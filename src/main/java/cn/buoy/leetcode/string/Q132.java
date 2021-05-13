package cn.buoy.leetcode.string;

public class Q132 {
    /**
     * https://www.youtube.com/watch?v=kTCymFbU2ok&t=7s
     * 不知道怎么想到的. 这种遍历顺序.
     */
    public static int minCut(String str) {
        if (str.isEmpty()) return 0;
        int len = str.length();
        int[] dp = new int[len];
        boolean[][] isPal = new boolean[len][len];

        //关键, 从头开始找dp[i], 后边的dp 依赖 前边的dp
        for (int hi = 0; hi < str.length(); hi++) {
            dp[hi] = hi;
            isPal[hi][hi] = true;
            //检查后半段是否为回文,
            for (int lo = 0; lo <= hi; lo++) {
                //只要lo == hi, 且( lo, hi是邻接点, 或者是相同点, 或, lo, hi 的内一层也是回文的话.
                if (str.charAt(lo) == str.charAt(hi) && (hi - lo <= 1 || isPal[lo + 1][hi - 1])) {
                    isPal[lo][hi] = true;
                    //如果lo等于0 就是dp[hi], 一刀都不用切
                    if (lo == 0) dp[hi] = 0;
                        //如果lo不是0, 那就是在 lo-1 和 lo 之间 切一刀即可, 就是 dp[lo - 1] + 1
                    else dp[hi] = Math.min(dp[lo - 1] + 1, dp[hi]);
                }
            }
        }
        return dp[len - 1];
    }
}
