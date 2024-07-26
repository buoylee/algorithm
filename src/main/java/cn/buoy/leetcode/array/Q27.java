package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q27 {
    /**
     * 27; 283
     * 簡單, 視頻.
     * https://www.youtube.com/watch?v=tFSOSgHX0pQ
     * 思路: 同向雙指針, i: int[] 遍歷 index; handlingIdx: 當前需要處理的 index, 0~handlingIdx-1 元素都已完成處理.
     * 發現 [i] 不是 target val 就賦值給 [handlingIdx], 然後 handlingIdx++
     */
    public int removeElement(int[] nums, int val) {
        int handlingIdx = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != val)
                nums[handlingIdx++] = nums[i];
        return handlingIdx;
    }

    /**
     * 都差不多簡單.
     * 双指针反向:
     * 思路: 将需要删除的数移动到倒数的位置,
     * 从头找到第一个删除的元素, 没到到则头指针++,
     * 直到找到, 与末尾指针元素交换(不管末尾指针是否是要删除的元素), 末尾指针++
     * 直到start <= end.
     */
    public int removeElement2(int[] nums, int val) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] == val) {
                nums[start] = nums[end];
                nums[end] = val;
                end--;
            } else {
                start++;
            }
        }
        return start;
    }

    public int removeElement3(int[] nums, int val) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            if (nums[i] == val) {
                nums[i--] = nums[l-- - 1];
            }
        }
        return l;

    }

    public static void main(String[] args) {
        Q27 q27 = new Q27();
//        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int[] nums = {4};
        int result = q27.removeElement(nums, 4);
        System.out.println(Arrays.toString(nums));
        System.out.println(result);
    }
}
