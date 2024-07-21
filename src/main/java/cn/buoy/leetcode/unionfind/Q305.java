package cn.buoy.leetcode.unionfind;

import java.util.*;

public class Q305 {
    /**
     * 代碼不好理解, 好好看註釋
     * https://www.youtube.com/watch?v=A6FeeG2JVnk
     * 思路: 轉爲 並查集.
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m <= 0 || n <= 0) return result;
        // 保存 每個格子的 最頂父節點.
        int[] roots = new int[m * n]; // one island = one tree
        Arrays.fill(roots, -1); // 初始化所有点都是水
        // 上下左右4個方向找 1
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // 當前 島數量
        int count = 0;
        // 遍歷陸地
        for (int[] position : positions) {
            // 一維的位置
            int currPos = position[0] * n + position[1];
            // 被提前找到, count 不變.
            if (roots[currPos] != -1) {
                result.add(count);
                continue;
            }
            roots[currPos] = currPos; // 变成陆地
            count++;
            // 上下左右4個鄰居, 找是否已經有陸地.
            for (int[] direction : directions) {
                int x = position[0] + direction[0];
                int y = position[1] + direction[1];
                // 關鍵: 爲什麼要使用1維arr 來保存 roots, 因爲即使 用 2維 arr 來保存, arr 中的值, 也是string 之類的類型, 還是要經過提取來得到 root 的位置.
                int neiborIdx = x * n + y;
                // 上下左右 鄰居 是 -1, 就不會 更新成 之前最頂 root(因爲第一個root 就是本身).
                if (x >= 0 && x < m && y >= 0 && y < n && roots[neiborIdx] != -1) {
                    // 鄰居 最頂root
                    int neiborRoot = find(roots, neiborIdx);
                    // 現在 node 不是 鄰居 node 的最頂root, 要合併.
                    if (currPos != neiborRoot) { // 若根节点不同，说明是不同的岛屿，合并
                        // 最頂root 更新 之前的 最頂root
                        roots[currPos] = neiborRoot;
                        // 一旦合完, 其他鄰居就不用檢查了, 把 currPos 改爲 舊的 root(neiborRoot)
                        currPos = neiborRoot;
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }

    // 找最頂的 parent, 直到 node 的 parent 是 "node 本身"(id != roots[id])
    private int find(int[] roots, int id) {
        while (id != roots[id]) {
            roots[id] = roots[roots[id]]; // 路径压缩
            id = roots[id];
        }
        return id;
    }

    // 上邊比較簡潔
    int[] parent;

    public List<Integer> numIslands22(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        parent = new int[n * m + 1];
        int numIslands = 0;
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            //化成1维, 从1开始
            int offset = x * n + y + 1;

            if (parent[offset] != 0) {
                // duplicate position detected (because all positions have parent 0 initially, in case the parent is not 0 we must have encountered the position before)
                ans.add(numIslands);
                continue;
            }

            parent[offset] = offset;
            numIslands++;

            //如果有相邻的`1`, 则属于相同的岛, 不用 numIslands++
            //up
            if (x > 0 && parent[offset - n] != 0 && union(offset, offset - n)) {
                numIslands--;    //check the grid on top of current grid
            }
            //down
            if (x < m - 1 && parent[offset + n] != 0 && union(offset, offset + n)) {
                numIslands--; // check the grid below current grid
            }
            //left
            if (y > 0 && parent[offset - 1] != 0 && union(offset, offset - 1)) {
                numIslands--; // check the grid to the left of the current grid
            }
            //right
            if (y < n - 1 && parent[offset + 1] != 0 && union(offset, offset + 1)) {
                numIslands--; // check the grid to the right of the current grid
            }
            ans.add(numIslands);
        }
        return ans;
    }

    //找root(parent[x] == x 的x)
    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    //当前node的parent指向相邻格子的parent
    private boolean union(int x, int y) {
        if (find(x) == find(y)) return false;
        parent[find(x)] = find(y);
        return true;
    }


    /**
     *
     */
    public List<Integer> numIslands23(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> land2id = new HashMap<Integer, Integer>();
        int num_islands = 0;
        int island_id = 0;

        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            Set<Integer> overlap = new HashSet<Integer>();

            if (land2id.containsKey(r * n + c)) {
                ans.add(num_islands);
                continue;
            }

            if (r - 1 >= 0 && land2id.containsKey((r - 1) * n + c)) {
                overlap.add(land2id.get((r - 1) * n + c));
            }
            if (r + 1 < m && land2id.containsKey((r + 1) * n + c)) {
                overlap.add(land2id.get((r + 1) * n + c));
            }
            if (c - 1 >= 0 && land2id.containsKey(r * n + c - 1)) {
                overlap.add(land2id.get(r * n + c - 1));
            }
            if (c + 1 < n && land2id.containsKey(r * n + c + 1)) {
                overlap.add(land2id.get(r * n + c + 1));
            }

            if (overlap.isEmpty()) {
                ++num_islands;
                land2id.put(r * n + c, island_id++);
            } else if (overlap.size() == 1) {
                land2id.put(r * n + c, overlap.iterator().next());
            } else {
                int root_id = overlap.iterator().next();
                for (Map.Entry<Integer, Integer> entry : land2id.entrySet()) {
                    int k = entry.getKey();
                    int id = entry.getValue();
                    if (overlap.contains(id)) {
                        land2id.put(k, root_id);
                    }
                }
                land2id.put(r * n + c, root_id);
                num_islands -= (overlap.size() - 1);
            }
            ans.add(num_islands);
        }

        return ans;
    }
}
