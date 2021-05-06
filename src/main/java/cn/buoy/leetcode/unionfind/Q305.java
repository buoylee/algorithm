package cn.buoy.leetcode.unionfind;

import java.util.*;

public class Q305 {
    /**
     * https://www.youtube.com/watch?v=A6FeeG2JVnk
     * 第一反应, 之前做过类似的bfs, 用不同数字标识`陆地`, 应该可以.
     * 题目的是有序的, numIslands递增没问题.
     */
    int[] parent;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
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
    public List<Integer> numIslands22(int m, int n, int[][] positions) {
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
