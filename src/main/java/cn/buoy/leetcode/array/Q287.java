package cn.buoy.leetcode.array;

import java.util.HashSet;

public class Q287 {

    /**
     * https://www.youtube.com/watch?v=6UaLpPavqoM
     * 转化为Floyd’s Algorithm求解(看Q142), 因为从题目可转换, 因为length为n+1的array, value在[1, n], 所以至少有2个index指向同一个value(一个闭环).
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        // 找到在环上的某一点相遇
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        // 找到进入环的点
        fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[fast];
            if (slow == fast) {
                break;
            }
        }

        return slow;
    }

    //空间复杂度为n, 不满足
    public int findDuplicate2(int[] nums) {
        HashSet<Integer> ints = new HashSet<Integer>();
        for (Integer n : nums) {
            boolean add = ints.add(n);
            if (!add)
                return n;
        }
        return -1;
    }

}
