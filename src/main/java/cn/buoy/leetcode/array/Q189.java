package cn.buoy.leetcode.array;

public class Q189 {
    /**
     * 简单, 视频, 有思路的话.
     * https://www.youtube.com/watch?v=06-7_TTlkAw
     * 思路: 1. 整體翻轉; 2. 翻轉前k个 num; 3. 翻轉 剩余的 後邊 nums.length-k 個 num.
     */
    public void rotate(int[] nums, int k) {
        // 例: [1, 2, 3, 4]; k == 2
        // 因为题目 rotate 可能超过 arr.len, 所以取余.
        k %= nums.length;
        // 整体反转
        // [4, 3, 2, 1]
        reverse(nums, 0, nums.length - 1);
        // 翻轉前k个 num
        // [3, 4, 2, 1]
        reverse(nums, 0, k - 1);
        // 翻轉 剩余的 後邊 nums.length-k 個 num
        // [3, 4, 1, 2]
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
