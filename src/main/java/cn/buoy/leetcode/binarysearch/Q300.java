package cn.buoy.leetcode.binarysearch;

import java.util.Arrays;

public class Q300 {
    public static void main(String[] args) {
        Q300 q300 = new Q300();
        int length = q300.lengthOfLIS(new int[]{1, 3, 4, 0, 2});
    }

    /**
     * DP: https://www.youtube.com/watch?v=7g3uoteFKug&ab_channel=%E5%AE%B0%E7%9B%B8%E5%B0%8F%E7%94%98%E7%BD%97
     * 二分: https://www.youtube.com/watch?v=l2rCz7skAlk
     * <p>
     * [1] 搜索值不是数组元素，且在数组范围内，从1开始计数，得“ - 插入点索引值”；所以, 索引值还是等于 – (length + 1)
     * <p>
     * [2] 搜索值是数组元素，从0开始计数，得搜索值的索引值；
     * <p>
     * [3] 搜索值不是数组元素，且大于数组内元素，索引值为 – (length + 1);
     * <p>
     * [4] 搜索值不是数组元素，且小于数组内元素，索引值为 – 1。
     */

    //2分: dp[i] 表示, 在 第i位 组成最大子序列的 最优 数字(越小越好), 遍历到每个元素, 都会更新到 符合 dp递增的 index上.
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for (int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            //– (length + 1) 运算完就会出现在正确的位置
            if (i < 0) i = -(i + 1);
            dp[i] = x;
            //index == len, 刚超, len++
            if (i == len) len++;
        }

        return len;
    }

    //dp[i] 表示, 在index == i, 存在最长子序列多少位.
    //关键: 转义方程: 找到 0~i-1中 比sums[i]小的 所有j的, 从中选出最大值 + 1, 就是 dp[i].
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        //[3, 2, 1, 4], 不能只有dp[0]=1, nums[1] 即使小于 nums[0] 还是 最长为1位.
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            //比 i前的 元素
            for (int j = 0; j < i; j++) {
                //比sum[i]小的, 都比一下, 选出最大的dp[j].
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;

    }
}
