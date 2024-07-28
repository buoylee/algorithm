package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q274 {
    /**
     * 理解 h index 就简单, 视频
     * https://www.youtube.com/watch?v=eBJZ3HnU1FE
     * 思路: 先由小到大排序, 从后往前检查, 保证 检查过的 paper 数量(从1开始) <= "当前遍历到的 paper 的 citation"
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        // 从只有1篇开始
        int count = 1;
        // 从 "引用最多的 paper"(end) 开始往前找
        for (int i = citations.length - 1; i >= 0; i--) {
            // 关键: 保证 当前 paper 数 <= "curr paper citation"
            if (count <= citations[i]) {
                count++;
            } else // 一旦 count paper 数 > "curr paper citation", 直接返回, 因为上一步 多++ 了一次, 记得 -1
                return count - 1;
        }
        return citations.length;
    }

    public int hIndex2(int[] citations) {
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
