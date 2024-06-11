package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q274 {
    /*
    https://www.youtube.com/watch?v=eBJZ3HnU1FE
    先大到小排序, 后用index sorting.
     */
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        int count = 1;

        for (int i = citations.length - 1; i >= 0; i--) {
            if (count <= citations[i]) {
                count++;
            } else {
                return count - 1;
            }
        }

        return count - 1;
    }

    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] buckets = new int[len + 1];
        for (int c : citations) {
            //桶排序, 统计不同引用数量, [ 0, 1, 2, 3, 4, 6->n ],
            if (c >= len) {
                buckets[len]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for (int i = len; i >= 0; i--) {
            //累计数量大于等于index, 则返回.
            count += buckets[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }
}
