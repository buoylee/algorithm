package cn.buoy.leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q253 {
    /**
     * 视频讲的好, 简单
     * https://www.youtube.com/watch?v=4MEkBvqE_2Q
     * 思路: 只要需要用 room 就会加入 PriorityQueue,
     * 如果 PriorityQueue 是空的直接加不用判断,
     * 如果 PriorityQueue 最小(早)的 end <= "curr meeting start", 可以使用 已使用结束的 room(poll 后, offer "curr meeting end"),
     * 如果不满足条件, 则 "curr meeting end" 直接加入 PriorityQueue,
     * 关键: 这样的操作最后留下的size就是曾经用房最多时的size.(可以看下边操作步骤好理解)
     */
    public int minMeetingRooms(int[][] intervals) {
        // intervals 先按 start 小到大排序.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 由小到大排序 依序加入其中的 intervals end
        PriorityQueue<Integer> endsQueue = new PriorityQueue<>((a, b) -> a - b);
        endsQueue.offer(intervals[0][1]);
        // 从 index == 1 开始 curr meeting start 和 queue top end 比较.
        for (int i = 1; i < intervals.length; i++) {
            // queue top end(已经开始的 meeting 最早结束的 end) 是否 <= curr meeting start, 表示可以腾空已使用结束的 room.
            if (endsQueue.peek() <= intervals[i][0])
                endsQueue.poll();
            endsQueue.offer(intervals[i][1]);
        }
        // 关键: 最后的size, 就是占用最多房间时候的大小.
        // 因为, 每开始一个 meeting, 就会入 queue,
        // 差别在于, 如果 queue top(最小end) 的 end <= curr meeting start(meeting 占用结束), 就会 poll 出 top, 然后加入 curr meeting end,
        // 这样的操作的结果, 就会保留曾经同时开会的最多数量.
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
