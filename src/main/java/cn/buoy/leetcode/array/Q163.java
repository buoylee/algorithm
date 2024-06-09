package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q163 {
    /**
     * https://www.youtube.com/watch?v=qUKn6Y0eryc
     * 实现 起来 没有那么快
     */
    public List<String> findMissingRanges(int[] arr, int low, int high) {
        List<String> res = new ArrayList<String>();

        // the next number we need to find
        int nextLow = low;

        for (int currentElement : arr) {
            // not within the range yet
            //跳过不在[lo, hi]范围的元素(小于low的)
            if (currentElement < nextLow) continue;

            // continue to find the next one
            //如next存在在a[]中, ++, 直到找到第一个不存在在a[]的next
            if (currentElement == nextLow) {
                nextLow++;
                continue;
            }

            // get the missing range string format
            //找到第一个不存在的next后, 找 index == i++ 的元素, 这个区间 就在[next,a[i] - 1]之间, 但是要考虑特殊情况,如果刚好 next == a[i] - 1, 则返回next str即可.
            res.add(getRange(nextLow, currentElement - 1));

            // now we need to find the next number
            //跳过a[i], next从 a[i] + 1 开始, 继续找 missing next
            //记得 a[i] + 1 还没检查.
            nextLow = currentElement + 1;
        }

        // do a final check
        //如果next后还没到 hi, 将`当前next`到`hi`作为剩余区间.
        if (nextLow <= high) res.add(getRange(nextLow, high));

        return res;
    }

    String getRange(int n1, int n2) {
        return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
    }
}
