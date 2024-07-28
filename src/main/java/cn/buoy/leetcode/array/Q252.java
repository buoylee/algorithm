package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q252 {
    /**
     * 太简单! 视频
     * https://www.youtube.com/watch?v=KgWzkT9FIYA
     * 思路: 先按 开会 start 排序, 然后判断是否 前一个 interval 的 end < 后一个 interval 的 start, 是则时间段, 没有重叠,
     * 即 只要出现重叠, 返回 false; 全部都不重叠, 返回 true(可以1个人开所有会)
     */
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null) return false;
        // 先按start排序
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        for (int i = 0; i < intervals.length - 1; i++) {
            // next.start < prev.end, 则false.
            // 根据leetcode 判定 边界重叠是可以的.
            if (intervals[i + 1][0] < intervals[i][1])
                return false;
        }
        return true;
    }
}
