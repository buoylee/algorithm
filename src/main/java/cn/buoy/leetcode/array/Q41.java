package cn.buoy.leetcode.array;

public class Q41 {
    /**
     * 簡單, hard? 自己能想到, 視頻
     * https://www.youtube.com/watch?v=jfb72FfxWKU
     * 思路: index sorting. 遍歷 i, 只要發現 nums[i] 的值(前提是 nums[i] 在題目範圍內(num <= 0 && num > nums.len)) 不在它的正確位置(i - 1)上, 就交換, 直到出現 "換回來 num" 不再題目範圍內, 停止.
     * //  0  1  2  3  4
     * // [1, 2, 3, 4, 5]
     * // nums[i] 的值, 應該放在 i - 1 (前提是 nums[i] 在題目範圍內(num <= 0 && num > nums.len))
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++)
            // index sorting
            // 忽略 題目範圍內(num <= 0 && num > nums.len) 的数, 對比 "nums[i] 的值" 是否不在 "对应位置"(i - 1), swap().
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i])
                swap(nums, i, nums[i] - 1);
        //从头開始檢查 nums[i] 与 i + 1 是否相等, 不是就 i + 1 返回.
        for (int i = 0; i < len; i++)
            if (nums[i] != i + 1)
                // 是返回 不存在的那個數, 不是 index
                return i + 1;
        // 都不缺, 就返回 nums.len++, 就是最後一個 nums[i]++
        return len + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
