package cn.buoy.leetcode.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class Q317 {
    /**
     * https://www.youtube.com/watch?v=F7AM7AGJZYE&t=56s
     * <p>
     * Step 1: start from every point 1 (building point), doing BFS traversal to fill out (or accumulate) distance array
     * (MUST start over and doing every BFS traversal individually)
     * <p>
     * Step 2: find minimum distance from dp array
     *
     * @param dp: store distance sum to all building for every point 0. Update values when we do BFS traversal
     * @param reach: store number of buildings that every point 0 can reach. Used for checking if current point is valid
     * while we want to find final result
     * @param countBuilding: count total number of point 1
     */
    final int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //到达各个房子最小距离和
        int[][] dp = new int[n][m];
        //能到达多少个房子
        int[][] reach = new int[n][m];
        int countBuilding = 0;
        Queue<int[]> queue = new LinkedList<>();

        // step 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    bfs(queue, grid, dp, reach, n, m);
                    //随便统计有几个房子(value为1的格子)
                    countBuilding++;
                }
            }
        }

        // step 2
        //遍历所有的格子, 找到 能到达所有房子的 格子(reach[i][j] == countBuilding), 然后取dp最小(累加 各房子到达步数)的.
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // WARNING: DO NOT FORGET to check whether current point is 0 and whether current point can reach all buildings
                if (grid[i][j] == 0 && reach[i][j] == countBuilding) {
                    result = Math.min(result, dp[i][j]);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public void bfs(Queue<int[]> queue, int[][] grid, int[][] dp, int[][] reach, int n, int m) {
        int level = 1;
        // DO NOT USE hashset, since hashset cannot determine whether it contains an array (coordinate)
        boolean[][] visited = new boolean[n][m];
        while (!queue.isEmpty()) {
            //bfs: 先记录前一层的元素个数, 接下来只会poll出前一层的元素个数, 并塞入他的下一层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curPoint = queue.poll();
                int x = curPoint[0];
                int y = curPoint[1];
                for (int j = 0; j < 4; j++) {
                    int dx = x + dir[j][0];
                    int dy = y + dir[j][1];
                    //跳过 访问过的 且 不是空地的 格子
                    if (dx < 0 || dx > n - 1 || dy < 0 || dy > m - 1 || grid[dx][dy] != 0 || visited[dx][dy]) {
                        continue;
                    }
                    queue.offer(new int[]{dx, dy});
                    visited[dx][dy] = true;
                    //从每个房子经过这格的 层数(步数) 累加
                    dp[dx][dy] += level;
                    //有几个房子可以经过这里.
                    reach[dx][dy]++;
                }
            }
            level++;
        }
    }
}
