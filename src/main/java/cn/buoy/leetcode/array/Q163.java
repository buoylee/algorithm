package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q163 {
    /**
     * 简单, 视频.
     * https://www.youtube.com/watch?v=qUKn6Y0eryc
     * 思路: 找不存在于 arr 的 num 作为 [start], 然后和 arr.nextEle - 1 作为 [end], 就是需要的区间,
     * 如果 "不存在于 arr 的 num" == "arr.nextEle - 1", 说明 "只是缺失了一个数".
     * 注意: 跳过不再 [low, high] 范围的 数; 和遍历完 arr 后, 补上 [不存在于 arr 的数, high]
     */
    public List<String> findMissingRanges(int[] arr, int low, int high) {
        List<String> res = new ArrayList<String>();
        // the next number we need to find
        int nextLow = low;
        for (int currentElement : arr) {
            // not within the range yet
            // 跳过不在[low, high]范围的元素(小于 low 的)
            if (currentElement < nextLow) continue;
            // continue to find the next one
            // 如果 nextLow 存在于 arr 中, ++, 直到找到第一个不存在于 a[] 的 nextLow
            if (currentElement == nextLow) {
                nextLow++;
                continue;
            }
            // get the missing range string format
            // 找到第一个不存在的next后, currentElement - 1 就是区间尾部, 即是 [next,a[i] - 1]; 如果刚好 next == a[i] - 1, 则返回 单个数 即可.
            res.add(getRange(nextLow, currentElement - 1));
            // now we need to find the next number
            // 跳过a[i], next从 a[i] + 1 开始, 继续找 missing next, 更新 nextLow
            nextLow = currentElement + 1;
        }
        // do a final check
        // 如果next后还没到 high, 将`当前next`到`high`作为剩余区间.
        if (nextLow <= high) res.add(getRange(nextLow, high));
        return res;
    }

    String getRange(int n1, int n2) {
        return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
    }
}
