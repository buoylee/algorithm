package cn.buoy.leetcode.dp;

public class Q256 {
    /**
     * 簡單, 視頻.
     * https://www.youtube.com/watch?v=K-G6pMiwb_k
     * 思路: 選顏色只會和 前一個房子 有關, 前邊選A, 那當前只能選B/C, 同理其他.
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length < 1 || costs[0].length < 1) return 0;
        //几间房子
        int houseLen = costs.length;
        //dp[i][j]表示, 涂到 第i个房子 涂得是j色, 那目前最少花费是 dp[i][j].
        int[][] dp = new int[houseLen][3];
        // init 第1間房 3种颜色可能
        for (int i = 0; i < 3; i++)
            dp[0][i] = costs[0][i];
        // 從第2間房開始遍歷
        for (int i = 1; i < houseLen; i++) {
            //关键: 如果当前房子涂 某個颜色(A)时, 則只能取 上一个房子 另外2种颜色(B/C)的 dp, 取 min, 再 + 當前房子顏色 cost.
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(Math.min(dp[houseLen - 1][0], dp[houseLen - 1][1]), dp[houseLen - 1][2]);
    }
}
