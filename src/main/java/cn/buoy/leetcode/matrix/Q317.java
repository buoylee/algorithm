package cn.buoy.leetcode.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class Q317 {
    /**
     * 簡單, 能想到的其實, 視頻, 代碼. 難點, 應該是步驟有點多, dfs要多看一下.
     * https://www.youtube.com/watch?v=F7AM7AGJZYE&t=56s
     * 思路: 要反過來, 從 每個 1 開始 dfs, 然後 計算 能到達的 所有 0 的步數, 遍歷完所有 1 後, 得到 '每個 1 到 0' 的 '最小的 累積總步數(dp[i][j])'.
     * 還需要一個 reachNums[i][j] 記錄 是否 所有的 1 都能到達 某個 0, 經過 0 時, ++就好.
     * 最後取出 'reachNums[i][j] == 1 的總數'的 dp[i][j], 得到最小那個 dp 就是答案.
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
        int rowLen = grid.length;
        int colLen = grid[0].length;
        // 某'0' 到达 各个'1' 的累加步數 的最小值.
        int[][] dp = new int[rowLen][colLen];
        // 某'0'能到达多少个'1'
        int[][] reachNums = new int[rowLen][colLen];
        int countBuilding = 0;
        Queue<int[]> queue = new LinkedList<>();
        // 遍歷所有 '1' 做 dfs
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    bfs(queue, grid, dp, reachNums, rowLen, colLen);
                    //累計有几个'1'
                    countBuilding++;
                }
            }
        }
        // step 2
        //dfs 所有 '0', 找到 能到达所有'1'的 '0'(reach[i][j] == countBuilding), 然后取dp(各個'1'到达某個'0'的累加步数)最小的.
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                // WARNING: DO NOT FORGET to check whether current point is 0 and whether current point can reach all buildings
                if (grid[i][j] == 0 && reachNums[i][j] == countBuilding) {
                    result = Math.min(result, dp[i][j]);
                }
            }
        }
        // 可能一個能到達所有'1'的'0'都沒有.
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public void bfs(Queue<int[]> queue, int[][] grid, int[][] dp, int[][] reachNums, int rowLen, int colLen) {
        int level = 1;
        // DO NOT USE hashset, since hashset cannot determine whether it contains an array (coordinate)
        boolean[][] used = new boolean[rowLen][colLen];
        while (!queue.isEmpty()) {
            //bfs: 先记录前一层的元素个数, 接下来只会poll出前一层的元素个数, 并塞入他的下一层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] currNode = queue.poll();
                // 4個方向
                for (int j = 0; j < 4; j++) {
                    int nextX = currNode[0] + dir[j][0];
                    int nextY = currNode[1] + dir[j][1];
                    //跳过 访问过的 且 不是空地的 格子
                    if (nextX < 0 || nextX > rowLen - 1 || nextY < 0 || nextY > colLen - 1 // 是否 超過 矩陣範圍
                            || grid[nextX][nextY] != 0
                            || used[nextX][nextY])
                        continue;
                    queue.offer(new int[]{nextX, nextY});
                    used[nextX][nextY] = true;
                    // 累加 从 每个'1' 到達 這個'0' 的层数(步数)
                    dp[nextX][nextY] += level;
                    // 有几个房子可以经过这里.
                    reachNums[nextX][nextY]++;
                }
            }
            // 這裏沒問題, level 是 從某個'1'開始到達當前'0' 的步數.
            level++;
        }
    }
}
