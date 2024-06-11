package cn.buoy.leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q253 {
    /**
     * https://www.youtube.com/watch?v=4MEkBvqE_2Q
     * 这个好理解,
     * 思路: 只要需要用房就会尝试往queue加,
     * 如果queue是空的直接加不用判断,
     * 如果queue最小(早)的结束时间满足当前开始时间, 可以替换(poll后offer),
     * 如果不满足, 则直接加入queue,
     * 这样的操作最后留下的size就是曾经用房最多时的size.
     */
    public int minMeetingRooms(int[][] intervals) {
        //[1,2]
        //[1,3][2,3];
        //[1,3][4,5];
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> endsQueue = new PriorityQueue<>((a, b) -> a - b);
        endsQueue.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            //前一个结束时间是否 <= 后一个开始时间, 是的话, 表示有空位.
            if (endsQueue.peek() <= intervals[i][0]) {
                endsQueue.poll();
            }
            endsQueue.offer(intervals[i][1]);
        }
        //最后的size, 其实是表示曾经出现过占用最多房间时候的大小, 因为只会poll出当时可以刚好可以接着开会的interval(实际endsQueue存的是结束时间, 用于片段是否可以接用).
        return endsQueue.size();
    }

    /**
     * 这个方法比较不好理解
     * https://www.youtube.com/watch?v=0roQnDBC27o
     * 对于252的解法: 因为目标是, 一个人能不能参加所有会议, 所以, 只需要检查后一个会议开始前, 前一个会议有没有结束.
     * 本题思路: [start, end], 对进出点进行排序, 联想进同一个房间同时有几个人, 在end没结束前, start为进房(++), 需要增加会议室,
     * <p>
     * (可以不看)第2种方式, 不好理解(道理差不多, 处理不太一样):
     * https://www.youtube.com/watch?v=3hOkj3IGFYk
     * 思路大致是, 合并不重叠的, 保留重叠的Interval, 最后 经历过几次 无法合并(和以往的 无法合并的queue中的所有Interval对比) 的情况, 就是需要几个
     */
    public int minMeetingRooms2(int[][] intervals) {
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
        int endsIndex = 0;
        for (int start : starts) {
            //实现稍有不同
            //每次遍历新的start 并与 ends[endsIndex]) 比较,如果新的start 还是比当前end早, 即是rooms(需要的room)++了;
            //联想进同一个房间同时有几个人(start比end领先几个), 每次遍历start都++(每次都), 而endsIndex++只出现在"start出现在end后"
            if (start < ends[endsIndex])
                rooms++;
            else
                //只是如果 不重叠 还会--, 等于`没加` 这个时候同时右移end指针 继续对比即可.
                endsIndex++;
        }
        return rooms;
    }
}
