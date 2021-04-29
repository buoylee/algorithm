package cn.buoy.leetcode.dp;

import java.util.Arrays;

public class Q174 {
    /**
     * https://www.youtube.com/watch?v=h7mrQrpti-k
     * 看视频好理解.
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        //dp[i][j] 表示的是 这个格子 需要的最少血量.
        //为什么需要对1行1列? 因为转义方程 需要 dp[i][j] 的右, 下各一格. 其`下格`表示从`上格`经过`本格`后剩余的最少血量. 同理`右格`表示 从 `左格`经过`本格`后 剩余的最少血量.
        int[][] dp = new int[m + 1][n + 1];
        for (int[] a : dp)
            Arrays.fill(a, Integer.MAX_VALUE);
        //设置从2个方向来的, 最后需要的最少血量.
        //target 的 下格
        dp[m][n - 1] = 1;
        //target 的 右格
        dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                //选取 经过该格后 剩余最少血量的值 - 当前dungeon格的值
                int need = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                //如果 差为负, 说明只要 能剩下一滴血来到这里, 都成功.
                dp[i][j] = need <= 0 ? 1 : need;
            }
        }
        return dp[0][0];
    }
}
