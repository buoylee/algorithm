package cn.buoy.leetcode.dp;

public class Q265 {
    /**
     * 256 思路: https://www.youtube.com/watch?v=K-G6pMiwb_k
     * <p>
     * https://www.youtube.com/watch?v=KVfQmGiwEMU
     * 和 256 差别不大
     */
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        //取出n个房子, k个颜色
        int n = costs.length, k = costs[0].length;

        //dp[i][j] indicate the optimal cost for the house i if it is painted with color j.
        int[][] dp = new int[n][k];
        //初始化第一间房
        for (int j = 0; j < k; j++)
            dp[0][j] = costs[0][j];

        //i房子, 从第2(index == 1)间房开始
        for (int i = 1; i < n; i++) {
            //j颜色, 对当前房子每个颜色进行 计算 可能的最少花费
            for (int j = 0; j < k; j++) {
                dp[i][j] = minCost(dp[i - 1], j) + costs[i][j];
            }
        }
        return minCost(dp[n - 1], -1);
    }


    /**
     * dp[i][j], 排除涂`j颜色`的情况下, 涂到 `i房子` 的 最小花费.
     * 这里的color是涂到 i - 1 房子为止 的 不同颜色的 最小花费 arr.
     */
    //find the minimum cost if the current house is painted with different color except j.
    //if j == -1, then find minimum cost for the current house comparing different color.
    private int minCost(int[] color, int j) {
        int min = Integer.MAX_VALUE;
        int i = 0;
        while (i < color.length) {
            if (j != -1 && i == j)
                ;
            else
                //为什么不 直接判断 是否 等于j 就好?
//            if (i != j)
                min = Math.min(min, color[i]);
            i++;
        }
        return min;
    }
}
