package cn.buoy.leetcode.binarysearch;

import java.util.Arrays;

public class Q300 {
    /**
     * 先看視頻了解思路, 就很好寫了.
     * DP, 看這個; DP比2分好理解, 2分比較巧妙: https://www.youtube.com/watch?v=7g3uoteFKug&ab_channel=%E5%AE%B0%E7%9B%B8%E5%B0%8F%E7%94%98%E7%BD%97
     * 二分, 看這個: https://www.youtube.com/watch?v=l2rCz7skAlk
     */

    //dp[i] 表示, 在index 爲 i作爲結尾的序列, 存在最长子序列長度.
    //关键: 转义方程: 找到 0~i-1中 比nums[i]小的所有nums[j]的dp[j], 从中选出其中dp[j]最大值 + 1, 就是 dp[i]. 看視頻最快了解思路.
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        // 每輪nums[i]都要從頭[0~(j-1)]比, 找出比當前nums[i]小的nums[j],比較所有nums[j], 得出其中最大的nums[j], 這時, nums[j] + 1 就是 nums[i].
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    //2分: dp[i] 表示, 在 第i位 (實際是表示的是, 從1個數字開始到n個數字組合的總位數 )组成最大子序列的最优数字(在相同位數時, 最後一位數字越小越好), 遍历到每个元素, 都会按照遞增順序, 更新(不是插入, 是替換!)到 符合递增的 index上. 看視頻一目瞭然.
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int x : nums) {
            //關鍵: –(length + 1) 运算完就会出现在正確替換的位置
            int i = Arrays.binarySearch(dp, 0, len, x);
            if (i < 0) i = -(i + 1);
            dp[i] = x;
            //index == len, 刚超, len++
            if (i == len) len++;
        }
        return len;
    }

    public static void main(String[] args) {
        Q300 q300 = new Q300();
        int length = q300.lengthOfLIS(new int[]{1, 3, 4, 0, 2});
    }

}
