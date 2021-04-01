package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q80 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int i = new Q80().removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        int time = 1;
        int check = 1;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] < nums[i]) {
                nums[check] = nums[i];
                check++;
                time = 1;
            } else if (time < 2) {
                nums[check] = nums[i];
                check++;
                time++;
            }
        }
        return check;
    }

    /*
    与前2个比,只要还是小,就覆盖.
     */
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }

}
