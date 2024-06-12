package cn.buoy.leetcode.array;

public class Q283 {
    /**
     * https://www.youtube.com/watch?v=3gPn77w8ezw
     * 双指针, i表示遍历到的index, j表示j之前的元素都不是0; i到末尾, j开始至0到末尾.
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        //需要插入`不为0的value`的index
        int insertPos = 0;
        //先完整处理不是0的数
        for (int num : nums) {
            if (num != 0)
                nums[insertPos++] = num;
        }

        //如果insertPos没到末尾, 则一直置0到末尾.
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}
