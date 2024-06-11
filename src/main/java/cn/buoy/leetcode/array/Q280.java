package cn.buoy.leetcode.array;

public class Q280 {
    /**
     * https://www.youtube.com/watch?v=2LyMqjS32aA
     * 从index第1个开始, 应该刚好是轮流递增递减, 每个元素都和前一个元素比较, 刚好能达到要求, [1, 4, 2, 1]->[1, 4, 1, 2]每次交换都会比需要比前前个小的时候更小, 反之亦然.
     * 每次交换, 都能保证前一个元素的位置合理, 如果递增, 交换了不影响前2个的合理性. 存在相同value 也是合理, 区别与 324题.
     */
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++)
            //要这么写, 不能合起来; 除非 else if加上 i % 2 == 0
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i])
                    swap(nums, i);
            } else if (nums[i - 1] < nums[i])
                swap(nums, i);
    }

    public void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;
    }
}
