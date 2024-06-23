package cn.buoy.leetcode.binarysearch;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Q349 {
    /**
     * https://www.youtube.com/watch?v=2yo0TDekO30
     * 太简单!
     * 方法1: 用set作爲查找, 先将其一放入 set, 再用 遍历另一arr是否包含相同元素, 有则放入resSet.
     * 方法2. 用sort arr作爲查找, 将其一先排序, 用 遍历另一arr元素 是否 在前者排序后arr中用 2分发查找, 存在则放入resSet.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> setForSearch = new HashSet<>();
        HashSet<Integer> resultSet = new HashSet<>();

        // 放入set
        for (int n : nums1) {
            setForSearch.add(n);
        }
        // nums2的元素 有存在在 setForSearch, 就加入result
        for (int n : nums2) {
            if (setForSearch.contains(n)) {
                resultSet.add(n);
            }
        }

        // 寫的太多, 其實就是把set轉int[]後直接返回
        int[] result = new int[resultSet.size()];
        int si = 0;
        for (Integer i : resultSet) {
            result[si] = i;
            si++;
        }
        return result;
    }
}
