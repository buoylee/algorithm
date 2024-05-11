package cn.buoy.leetcode.array;

import java.util.LinkedList;
import java.util.List;

public class Q57 {
    /**
     * https://www.youtube.com/watch?v=w3xgyRJ5egY
     * 简单
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        //将比 new头 还小的原元素 先放到res
        while (i < intervals.length && intervals[i][1] < newInterval[0])
            result.add(intervals[i++]);
        // merge all overlapping intervals to one considering newInterval
        //merge 或者 插入new, 下边这个循环就可以满足 多种情况
        //直到 出现原元素头 大于 new的尾 停止
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval = new int[]{ // we could mutate newInterval here also
                    //头找最小的
                    Math.min(newInterval[0], intervals[i][0]),
                    //尾找最大的
                    Math.max(newInterval[1], intervals[i][1])};
            i++;
        }
        result.add(newInterval); // add the union of intervals we got

        // add all the rest
        //将剩余 不需要处理的原元素放入res
        while (i < intervals.length) result.add(intervals[i++]);

        int[][] res = result.toArray(new int[result.size()][]);
        return res;
    }
}
