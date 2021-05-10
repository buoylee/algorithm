package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q56 {
    /**
     * https://www.youtube.com/watch?v=uiOq8TO5CbE
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return res.toArray(new int[0][]);

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        //current start, end
        int start = intervals[0][0];
        int end = intervals[0][1];

        //这里多比了一次 intervals[0], 无所谓
        for (int[] i : intervals) {
            if (i[0] <= end) {
                //有overlap
                //排完序后, start肯定时min, 跳过判断; 选出max end即可.
                //如果有重叠, 还需要继续判断与下一个interval关系, 不能直接插入res
                end = Math.max(end, i[1]);
            } else {
                //无overlap, 直接插入 current,
                res.add(new int[]{start, end});
                //然后current 指向 下一个interval
                start = i[0];
                end = i[1];
            }
        }
        //记得最后一个还没插, 插一下.
        res.add(new int[]{start, end});
        return res.toArray(new int[0][]);
    }
}
