package cn.buoy.leetcode.dp;

public class Q63 {
    /**
     * 和 62 差别不大, 不同在 有障碍.
     * 簡單, 理解思路, 实现不一样:
     * https://www.youtube.com/watch?v=sJo09lnyBqM
     * 思路: 先初始化第一行/列, 遇到障碍, 就设置为0即可. dp[i][j] = dp[i - 1][j] + dp[i][j - 1],
     * <p>
     * 可以优化代码, 在第一行第一类可以不用初始化, 在遍历dp的时候, 加上 m-1 或者 n-1 < 0时, 不用统计`左边`或者`上边`的情况.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowLen = obstacleGrid.length;
        int colLen = obstacleGrid[0].length;
        // dp[row][col] 表示 走到這格的所有方法. 走到 dp[row][col] 的方式只有2种, dp[row-1][col] 或者 dp[row][col-1]
        int[][] dp = new int[rowLen][colLen];
        if (obstacleGrid[0][0] == 1) return 0;
        dp[0][0] = 1;
        // 对第一列 初始化
        for (int i = 1; i < rowLen; i++) {
            // dp 都设为1, 只要遇到障碍, 就break, 因为后边都不可达.
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        // 行同理
        for (int j = 1; j < colLen; j++) {
            // dp 都设为1, 只要遇到障碍, 就break, 因为后边都不可达.
            if (obstacleGrid[0][j] == 1) break;
            dp[0][j] = 1;
        }
        // 同 62 题, 计算 2种情况的和 即可.
        for (int i = 1; i < rowLen; i++) {
            for (int j = 1; j < colLen; j++) {
                //If there isn't an obstacle, then we can add it
                if (obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else // 遇到障碍, 直接设0
                    dp[i][j] = 0;
            }
        }
        return dp[rowLen - 1][colLen - 1];
    }
}
