package cn.buoy.leetcode.bitmanipulation;

public class Q338 {
    /**
     * 不太會考, 直接看視頻
     * https://www.youtube.com/watch?v=6VkD1Q9eSpU
     * https://www.youtube.com/watch?v=HpG4KX_DXBk 短, 转义方程易理解
     * 思路: dp, 存在着 dp[i] 与 dp[i - (i & -i)] 差值为 最后一个出现 '1' 的 bit, 所表示的值, 这一关系,
     * 所以, 转义方程为: dp[i] = dp[i - (i & -i)] + 1;
     */
    public int[] countBits2(int num) {
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++)
            // 关键: dp[i] 与 dp[i - (i & -i)] 差值为 最后一个出现 '1' 的 bit, 所表示的值.
            dp[i] = dp[i - (i & -i)] + 1;
        return dp;
    }

    // 这个感觉单纯就是找规律
    public int[] countBits(int num) {
        // dp[i] 表示 数字为i 是有几个1
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++)
            // 等于 2分之一 + 是否 奇偶数 + 1
            dp[i] = dp[i >> 1] + (i & 1);
        return dp;
    }
}
