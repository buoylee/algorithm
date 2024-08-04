package cn.buoy.leetcode.dp;

import java.util.Arrays;

public class Q174 {
    /**
     * 稍微看视频, 然後看註釋, 好理解.
     * https://www.youtube.com/watch?v=h7mrQrpti-k
     * 思路: 經典 dp, 走4邊形到右下角倒序版. 關鍵理解 dp[i][j]代表的意思.
     * dp[i][j] 有2種走法, 下 或 右,
     * 如果要走到終點, 就需要在到達之前的每一步都需要 >= 1,
     * 如果是最少需要多少血 這個條件, 還要選擇 可行的最小值 作爲這個格子的剩餘血量.
     * 我們從最後一步開始判斷, 要找到 dp[i][j] 最小, 就要檢查 他的 下一步的可能性作爲反推, 就是 dp[i + 1][j](右) 和 dp[i][j + 1](下),
     * dp[i + 1][j]/dp[i][j + 1] + dungeon[curri][currj] 就是 dp[i][j] 需要的最小值的可能值, 我們還要保證 >=0 和 取最小值.
     * 也可以理解为 从 dp[i][j] 的 下/右 开始(起始至少1血), 走到 dp[i][j], 则需要 1 - dungeon[i][j]..., 直到开头
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int rowLen = dungeon.length;
        int colLen = dungeon[0].length;
        // 關鍵: dp[i][j] 表示的是 这个格子 需要的最少血量.
        // 为什么需要增加 1行1列? 因为转义方程 需要 dp[i][j] 的右, 下各一格. Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]
        // 其`下格`表示从`上格`经过`本格`后剩余的最少血量.
        // 同理`右格`表示 从 `左格`经过`本格`后 剩余的最少血量.
        // 也可以理解为 从 dp[i][j] 的 下/右 开始(起始至少1血), 走到 dp[i][j], 则需要 1 - dungeon[i][j]..., 直到开头
        int[][] dp = new int[rowLen + 1][colLen + 1];
        for (int[] a : dp)
            Arrays.fill(a, Integer.MAX_VALUE);
        //设置从2个方向来的, 最后需要的最少血量.
        //target 的 下格 和 右格
        dp[rowLen][colLen - 1] = 1;
        dp[rowLen - 1][colLen] = 1;
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                //选取 经过该格后 剩余最少血量的值 - 当前dungeon格的值
                int need = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                //如果 差为负, 说明只要 能剩下一滴血来到这里, 都成功.
                dp[i][j] = need <= 0 ? 1 : need;
            }
        }
        return dp[0][0];
    }
}
