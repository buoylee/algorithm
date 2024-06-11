package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q252 {
    /**
     * https://www.youtube.com/watch?v=KgWzkT9FIYA
     * 太简单!
     */
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null) {
            return false;
        }
        //先按start排序
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        for (int i = 0; i < intervals.length - 1; i++) {
            //next.start < prev.end, 则false.
            //根据leetcode 判定 边界重叠是可以的.
            if (intervals[i + 1][0] < intervals[i][1]) {
                return false;
            }
        }
        return true;
    }
}
