package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q56 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=uiOq8TO5CbE
     * 思路: 先用 interval 的 start 排序, 然後比較 "前一個的 end" 是否 >= "後一個的 start", 是就時間重疊, 可以合併(合併後的 end 要選2者中大的).
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return result.toArray(new int[0][]);
        // 按 start 排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 用 "第一個 interval" 作爲 "當前 interval" 的 start/end
        int start = intervals[0][0];
        int end = intervals[0][1];
        // 这里多比了一次 intervals[0], 无所谓
        for (int[] i : intervals) {
            // 如果 後一個的 start <= 前一個的 end, 時間重疊.
            if (i[0] <= end) {
                // 如果有重叠, 選2者中 "大的 end" 作爲 "合併後的 end"
                end = Math.max(end, i[1]);
            } else {
                // 无 overlap, 直接插入 current,
                result.add(new int[]{start, end});
                // 无 overlap, 就要更新 current interval 用作 下一輪比較.
                start = i[0];
                end = i[1];
            }
        }
        // 前邊的操作, 只會在 "無 overlap" 時, 插入前一個 interval, 所以, 记得剩最後一個 current 的時候插一下.
        result.add(new int[]{start, end});
        return result.toArray(new int[0][]);
    }
}
