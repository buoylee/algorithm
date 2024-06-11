package cn.buoy.leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class Q219 {
    /*
    一眼過
    从头开始插入set, 当超过间隔k, 先删掉最早index元素, 后假如新元素, 插入失败则返回true.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) return true;
        }
        return false;
    }
}
