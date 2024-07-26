package cn.buoy.leetcode.array;

public class Q53 {
    /**
     * 不好想, 視頻
     * https://www.youtube.com/watch?v=EcJ0wBBMoKA
     * 思路: dp[i] 表示, i 作爲 subArr 末尾 index 時(head 不一定是 index0), 最大可能的 sum.
     * dp[i] = max(dp[i - 1] + nums[i], nums[i]),
     * 可以看出, 如果 dp[i - 1] <= 0, 就完全拋棄掉 i 前邊的 arr.
     * 如果 dp[i - 1] > 0, 就是對 dp[i] 有幫助(增加), 就加上 "前邊的 "最大 subArr sum"
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 到当前元素为止, 最大的subarray sum 值
        int[] dp = new int[n]; // dp[i] means the maximum subarray ending with A[i];
        dp[0] = nums[0];
        int max = dp[0];
        // 從 i == 1 開始, 遍歷 i, 檢查 dp[i - 1] 是否有幫助(增加), 沒有就捨棄. 出現更大 dp[i] 則更新 max
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] + (Math.max(dp[i - 1], 0));
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
