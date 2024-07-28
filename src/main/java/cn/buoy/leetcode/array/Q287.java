package cn.buoy.leetcode.array;

import java.util.HashSet;

public class Q287 {
    /**
     * 142
     * 简单, 视频
     * https://www.youtube.com/watch?v=6UaLpPavqoM
     * 题目理解: 因为 arr.len == n, 且 ele 范围在 1 ~ n+1, 所以至少有2个 index 指向同一个 num(有环).
     * 思路: 快慢指针证明有环. 转化为 Floyd’s Algorithm 求解, 把 i 和 nums[i] 视为, node 和 "node 指向的 "下一个 node", 证明有环.
     */
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        // 1. 找到快慢指针在环上的相遇点.
        while (true) {
            slow = nums[slow];
            // 走2步
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        // 2. 找到进入环的点.
        // 当找到 快慢指针在环上的相遇点, 归零其中一个指针, 然后以相同的速度前进, 直到再次相遇, 就是 环的入口.
        fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[fast];
            if (slow == fast) break;
        }
        return slow;
    }

    // 空间复杂度为n, 不满足
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
