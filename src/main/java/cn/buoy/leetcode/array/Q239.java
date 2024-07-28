package cn.buoy.leetcode.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q239 {
    /**
     * https://www.youtube.com/watch?v=uvsRkNiL0e0
     * 思路: 每当有元素加入queue前, 都会清掉前边已经超过窗口范围的index, (关键点)然后`从队尾`把小于当前value的元素的index逐一弹出, 最后放入当前index, 队头就是res[i]的答案.
     * 这样做是为了保证新加入元素后, 整个queue仍然是有序的(因为最新位置元素的存在, 小于这个当前元素的其他还在范围内的元素没有比较的意义, 因为范围内最大数不会是那些比当前元素小的元素.)
     * 总结:
     * 每次都先保证queue内元素 index合法,
     * 然后保留大于当前元素的元素, 因为只要没越界, 比当前元素大的 之前的元素 都会保留 除非越界; 一旦出现有比之前元素大的元素时, 会把小于当前元素的 前元素都赶出去.
     * 如果当前value就是最大最, 则会弹出之前的所有元素.
     * 从而保证了queue头的, 肯定是当前最大的元素.
     */
    public int[] maxSlidingWindow(int[] arr, int width) {
        if (arr == null || width <= 0) return new int[0];
        int len = arr.length;
        int[] result = new int[len - width + 1];
        int resultIndex = 0;
        // store index
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            // remove numbers out of range k
            // 检查队头是否有超过当前范围的index, 有则都踢出去
            while (!queue.isEmpty() && queue.peek() < i - width + 1)
                queue.poll();
            // remove smaller numbers in k range as they are useless
            // 关键: 当前value 从queue尾开始对比, 逐一弹出小于当前value的index, 直到queue有大于当前value的元素时停止.
            // 这种方式, 保证了在窗口内, 最大值在栈顶, 且栈内按 递减顺序排列, 弹出的元素因为小于 "刚进入窗口范围内的当前元素", 不会对result有影响.
            while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i])
                queue.pollLast();
            // q contains index... r contains content
            // 放入当前index
            queue.offer(i);
            // 当i位置, 已经满k个元素, 就可以开始填充第一个 result 元素.
            if (i >= width - 1)
                result[resultIndex++] = arr[queue.peek()];
        }
        return result;
    }
}
