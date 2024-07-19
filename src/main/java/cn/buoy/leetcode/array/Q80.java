package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q80 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=JimP_qCjb0Q
     * 思路: 遍歷到 nums[handling] 只需要檢查 是否和 nums[handling - 2] 相同, 如果相同就 i++, 直到遇到 和 handling-2 不同的, 然後賦值即可.
     * 所以需要 一個變量 handling 來記錄, 需要 替換的位置.
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int handling = 2;  // 从第三个元素开始检查
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[handling - 2]) {  // 当前元素和两次前的元素不相同
                nums[handling] = nums[i];
                handling++;
            }
        }
        return handling;
    }

    public int removeDuplicates2(int[] nums) {
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
    public int removeDuplicates3(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int i = new Q80().removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
}
