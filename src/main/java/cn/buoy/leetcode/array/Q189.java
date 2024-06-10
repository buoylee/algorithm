package cn.buoy.leetcode.array;

public class Q189 {
    // https://www.youtube.com/watch?v=06-7_TTlkAw
    // 思路1: 整體翻轉, 翻轉前k, 在翻轉後邊nums.length-k個.
    public void rotate(int[] nums, int k) {
        //整体反转 [1, 2, 3, 4]
        //k == 2
        k %= nums.length;
        //[4, 3, 2, 1]
        reverse(nums, 0, nums.length - 1);
        //[3, 4, 2, 1]
        reverse(nums, 0, k - 1);
        //[3, 4, 1, 2]
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
