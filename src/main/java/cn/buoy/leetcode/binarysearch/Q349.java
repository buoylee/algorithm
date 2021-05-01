package cn.buoy.leetcode.binarysearch;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Q349 {
    /**
     * 太简单!
     * 1. 先将其一放入 set, 在用 遍历另一arr是否包含相同元素, 有则放入resSet.
     * 2. 将其一先排序, 用 遍历另一arr元素 是否 在前者排序后arr中用 2分发查找, 存在则放入resSet.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> setIn = new HashSet<>();
        HashSet<Integer> resSet = new HashSet<>();

        for (int n : nums1) {
            setIn.add(n);
        }
        for (int n : nums2) {
            if (setIn.contains(n)) {
                resSet.add(n);
            }
        }
        int[] res = new int[resSet.size()];
        int si = 0;
        for (Integer i : resSet) {
            res[si] = i;
            si++;
        }
        return res;
    }
}
