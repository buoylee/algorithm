package cn.buoy.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Q350 {
    /**
     * https://www.youtube.com/watch?v=PIyn0fpidyM
     * 簡單.
     * <p>
     * https://www.youtube.com/watch?v=AjU21ITjQw4
     * 1. 只是在349基礎上, 把set變爲map,  增加記錄對應元素出現的次數, 然後在檢查交集時, --次數就好.
     * <p>
     * 2. 先排序2arr, 双指针同向, 如果 value 相同, index 同时 ++ 并加入res,
     * 不同, 则value小的 index++, 直到 再次value 相同.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        //可以省
        int[] first = null;
        int[] second = null;
        if (nums2.length > nums1.length) {
            first = nums2;
            second = nums1;
        } else {
            first = nums1;
            second = nums2;
        }

        HashMap<Integer, Integer> inMap = new HashMap<>();
        ArrayList<Integer> resList = new ArrayList<>();
        Arrays.stream(first).forEach(n -> {
            inMap.put(n, inMap.getOrDefault(n, 0) + 1);

        });
        Arrays.stream(second).forEach(n -> {
            if (inMap.getOrDefault(n, 0) > 0) {
                inMap.put(n, inMap.getOrDefault(n, 0) - 1);
                resList.add(n);
            }
        });
        int[] res = new int[resList.size()];
        int si = 0;
        for (Integer i : resList) {
            res[si] = i;
            si++;
        }
        return res;
    }
}
