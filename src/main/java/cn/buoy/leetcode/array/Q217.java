package cn.buoy.leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class Q217 {
    /**
     * 超簡單一眼過
     * https://www.youtube.com/watch?v=4rE2t0VlDVQ
     * 思路: ele 全部放 set, 如何 出现 !set.add(i), 说明有重复.
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i : nums)
            if (!set.add(i))// if there is same
                return true;
        return false;
    }
}
