package cn.buoy.leetcode.array;

import java.util.HashSet;
import java.util.Set;

// 超簡單一眼過
public class Q217 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i : nums)
            if (!set.add(i))// if there is same
                return true;
        return false;
    }
}
