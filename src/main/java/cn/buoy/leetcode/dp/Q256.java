package cn.buoy.leetcode.dp;

public class Q256 {
    /**
     * https://www.youtube.com/watch?v=K-G6pMiwb_k
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length < 1 || costs[0].length < 1) return 0;

        //几间房子
        int xlen = costs.length;
        //dp[i][j]表示 涂到第i个房子如果涂得是j色,  那目前最少花费是 dp[i][j].
        int[][] dp = new int[xlen][3];

        for (int c = 0; c < 3; c++) dp[0][c] = costs[0][c];

        //跳过第一间(index == 0)
        for (int i = 1; i < xlen; i++) {
            //关键: 找出 当前房子 涂 不同颜色时, 与上一个房子dp 不同颜色的最小值 的 和, 取最小值.
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        //最后返回最后一间房 涂哪种颜色 的dp 最小.
        return Math.min(Math.min(dp[xlen - 1][0], dp[xlen - 1][1]), dp[xlen - 1][2]);
    }
}
