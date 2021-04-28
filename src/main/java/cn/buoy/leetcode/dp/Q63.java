package cn.buoy.leetcode.dp;

public class Q63 {
    /**
     * 理解思路, 实现不一样: https://www.youtube.com/watch?v=sJo09lnyBqM
     * 和 62 差别不大, 留意处理 障碍.
     * <p>
     * 思路:遇到障碍, 就设置为0.
     * 可以优化代码, 在第一行第一类可以不用初始化, 在遍历dp的时候, 加上 m-1 或者 n-1 < 0时, 不用统计`左边`或者`上边`的情况.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //行
        int m = obstacleGrid.length;
        //列
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        if (obstacleGrid[0][0] == 1)
            return 0;

        dp[0][0] = 1;

        //对第一列 初始化
        for (int i = 1; i < m; i++) {
            //只要遇到0, 就break, 因为后边都不可达, 否则 都是设为1.
            if (obstacleGrid[i][0] == 1) break;
            else dp[i][0] = 1;
        }

        //同理第一列, 对第一行 初始化
        for (int j = 1; j < n; j++) {
            //只要遇到0, 就break, 因为后边都不可达, 否则 都是设为1.
            if (obstacleGrid[0][j] == 1) break;
            else dp[0][j] = 1;
        }

        //同 62 题, 计算 2种情况的和 即可.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //If there isn't an obstacle, then we can add it
                if (obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else
                    dp[i][j] = 0;
            }
        }

        return dp[m - 1][n - 1];
    }
}
