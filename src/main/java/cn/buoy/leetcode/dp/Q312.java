package cn.buoy.leetcode.dp;

public class Q312 {
    public static void main(String[] args) {
        Q312 q312 = new Q312();
        int i = q312.maxCoins(new int[]{3, 1, 5, 8});
    }

    /**
     * https://www.youtube.com/watch?v=Ci39lcoLbyw
     * 视频简述: 第一层for 控制距离, 第2层控制left, 第3层遍历left+1, right-1 之间的所有可能数
     * 转义方程不好想.
     * 视频参考思路, 思想有区别.
     */
    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        //从1开始填入数字, 且排除掉`0元素`, 头尾index 填1.
        int n = 1;
        for (int x : iNums)
            if (x > 0)
                nums[n++] = x;
        nums[0] = nums[n++] = 1;

        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }

    public int burst(int[][] memo, int[] nums, int left, int right) {
        //中间的球都破了.
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        //dp[left][right]就是, 爆掉left + 1 和 right - 1 之间的气球 的最大数. 爆掉气球分割前后2部分, 递归求解.
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                    + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        memo[left][right] = ans;
        return ans;
    }
}
