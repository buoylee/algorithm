package cn.buoy.leetcode.dp;

public class Q62 {
    /**
     * 直接看視頻, 超簡單.
     * https://www.youtube.com/watch?v=L6dWXuh8BuE
     * 思路: 先初始化 第一行/列, 然后 dp[i][j] == 上格(dp[i-1][j]) 和 左格(dp[i][j-1]) 的和, 直到最後.
     * <p>
     * 可以简化为1维dp, 不过稍微不好理解点.
     */
    public int uniquePaths(int row, int col) {
        Integer[][] dp = new Integer[row][col];
        // 第一行, 第一列都是 只有1种走法, 一路到底.
        for (int i = 0; i < row; i++)
            dp[i][0] = 1;
        for (int j = 0; j < col; j++)
            dp[0][j] = 1;
        // 关键: 走到 dp[x][y] 的方式只有2种, dp[x-1][y] 或者 dp[x][y-1].
        // 細節: row, col 都從1開始即可, 因爲初始化以完成操作.
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
        return dp[row - 1][col - 1];
    }
}
