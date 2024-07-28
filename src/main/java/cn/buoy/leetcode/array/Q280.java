package cn.buoy.leetcode.array;

public class Q280 {
    /**
     * 324题
     * 发现规律就简单
     * https://www.youtube.com/watch?v=2LyMqjS32aA
     * 思路: 从 index == 1 开始, 比较 nums[i - 1] 和 nums[i] 的大小, 按照题目要求, 在奇/偶数位, 比较完, 不符合题意的要求就 swap() 即可, 就是这么简单.
     * 关键: 每次将不符合题意的 nums[i - 1] 和 nums[i] swap, 都不会影响 之前已排好的 arr.
     * 奇数: [1, 4, 2, 1(i)]->[1, 4, 1, 2]; 每次交换完, nums[i - 1] (1) 都会比 "原来的 nums[i - 1] (2)" 更小
     * 偶数: [1, 2, 3(i), 2]->[1, 3, 2, 2]; 每次交换完, nums[i - 1] (3) 都会比 "原来的 nums[i - 1] (2)" 更大
     */
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++)
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i])
                    swap(nums, i);
            } else {
                if (nums[i - 1] < nums[i])
                    swap(nums, i);
            }
    }

    public void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;
    }
}
