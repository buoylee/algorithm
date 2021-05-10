package cn.buoy.leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q253 {
    /**
     * https://www.youtube.com/watch?v=0roQnDBC27o
     * [i, j], 对进出点进行排序, i为进(++), j为出(--), 算最大值, 即 遍历到 最后一个 i, 退出 遍历.
     * <p>
     * 第2种方式, 不好理解:
     * https://www.youtube.com/watch?v=3hOkj3IGFYk
     * 思路大致是, 合并不重叠的, 保留重叠的Interval, 最后 经历过几次 无法合并(和以往的 无法合并的queue中的所有Interval对比) 的情况, 就是需要几个
     */
    public int minMeetingRooms(int[][] intervals) {
        // public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        //分别排序 开始, 结束 arr
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for (int i = 0; i < starts.length; i++) {
            //实现稍有不同
            //每次遍历新的start 并与 ends[endsItr]) 比较,如果新的start 还是比当前end早, 即是rooms(需要的room)++了;
            if (starts[i] < ends[endsItr])
                rooms++;
            else
                //只是如果 不重叠 还会--, 等于`没加` 这个时候同时右移end指针 继续对比即可.
                endsItr++;
        }
        return rooms;
    }

    public int minMeetingRooms2(int[][] intervals) {
        //[1,2]
        //[1,3][2,3];
        //[1,3][4,5];
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int[] interval : intervals) {
            if (pq.size() == 0) {
                pq.offer(interval);
            } else {
                if (pq.peek()[1] <= interval[0]) {
                    pq.poll();
                }
                pq.offer(interval);
            }
        }
        return pq.size();
    }
}
