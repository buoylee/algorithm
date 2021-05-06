package cn.buoy.leetcode.unionfind;

public class Q323 {
    /**
     * https://www.youtube.com/watch?v=yEbHM_XLNw0
     * 方法2: 构建邻接表, 对每个点 dfs, 经过设为visited, 每次一整轮dfs结束时, count++
     */
    public int countComponents(int n, int[][] edges) {
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

    public int find(int[] roots, int id) {
        //如果当前的父节点 不是父节点自己
        while (roots[id] != id) {
            //压缩路径, 可不写; a->b->c  => a->c
            roots[id] = roots[roots[id]];  // optional: path compression
            //将id 设为父节点, 继续往上找
            id = roots[id];
        }
        return id;
    }
}
