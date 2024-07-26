package cn.buoy.leetcode.array;

import java.util.LinkedList;
import java.util.List;

public class Q57 {
    /**
     * 简单, 視頻, 或直接看代碼
     * https://www.youtube.com/watch?v=w3xgyRJ5egY
     * 思路: 分3個區域, (end < newIntervalStart)intervals < [start new interval end](可能需要合併的區域) < (newIntervalEnd < start)intervals;
     * 先插入 不需要和 newInterval 合併的 intervals,
     * 然後合併 newInterval 和 "原 intervals", 直到出現"new interval end" < "原 interval start" 停止,
     * 最後把剩餘的 "原 intervals" 插入即可.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        // 先把 "interval end" < "new interval start" 的 "原 interval" 先放到res
        while (i < intervals.length && intervals[i][1] < newInterval[0])
            result.add(intervals[i++]);
        // merge all overlapping intervals to one considering newInterval
        // "原 intervals" 合併 "new interval" 的範圍
        // 關鍵: 直到出现 "new interval end" < "原 interval start" 停止. 即 出現 不能和 new interval 合併時, 停止.
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval = new int[]{ // we could mutate newInterval here also
                    // 關鍵: 因爲, 合併範圍的 start/end 都是是具體情況而定, 這裏只能確定他們需要合併, 所以:
                    // 头找最小的
                    Math.min(newInterval[0], intervals[i][0]),
                    // 尾找最大的
                    Math.max(newInterval[1], intervals[i][1])};
            i++;
        }
        // 合併完記得插入
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        // 将 剩余 "原 intervals" 放入 result
        while (i < intervals.length) result.add(intervals[i++]);
        return result.toArray(new int[result.size()][]);
    }
}
