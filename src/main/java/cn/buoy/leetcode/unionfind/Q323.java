package cn.buoy.leetcode.unionfind;

public class Q323 {
    /**
     * 簡單, 視頻, 並查集.
     * https://www.youtube.com/watch?v=yEbHM_XLNw0 失效
     * https://www.youtube.com/watch?v=fskPWs3Nuhc
     * 思路: 並查集, 在 union 時, 總 node 數 -- 即可.
     * <p>
     * 方法2: 构建邻接表, 对每个点 dfs, 经过设为visited, 每次一整轮dfs结束时, count++
     */
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges)
            uf.union(edge[0], edge[1]);
        return uf.getCount();
    }

    class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // 找 "最頂root"
        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        // 處理邊, 也就是把邊的2點連起來, 成爲一個 union,
        public void union(int x, int y) {
            // 找 "最頂root"
            int rootX = find(x);
            int rootY = find(y);
            // 連接時, 把在 "同一個 union" 下的 "2個 node" 的 parent 都設置爲 "最頂 root"(通過上邊 find() 找到)
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                // 關鍵: 只要在 "總點數 n" 上減就好了, 被 union, 就是把 "獨立的 node" 和另一個 union 合併, 直接減1即可.
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    // 上邊是經典 unionfind 經典寫法.
    public int countComponents2(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) roots[i] = i;

        for (int[] e : edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            //表示 假如之前 2点 没有连过,
            //接下来 会连一起, 就是有node加入了此阵营, 剩余的block数 n--
            if (root1 != root2) {
                roots[root1] = root2;  // union
                //初始 每个数 各自为block
                //即使是 XX -- XX, 这样2block连一起, 也只是减少1, 没问题.
                n--;
            }
        }
        return n;
    }

    // 找頂級父節點
    public int find(int[] roots, int id) {
        // 如果当前的父节点 不是节点自己
        while (roots[id] != id) {
            // 用 "id 的 祖父節點" 成爲 "id 的 父節點"
            roots[id] = roots[roots[id]];  // optional: path compression
            //将id 设为父节点, 继续往上找
            id = roots[id];
        }
        return id;
    }
}
