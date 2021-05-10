package cn.buoy.leetcode.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q239 {
    /**
     * https://www.youtube.com/watch?v=uvsRkNiL0e0
     * 思路: 每当有元素加入queue前, 都会清掉前边已经路过的index, 然后`从队尾`把小于当前value的 index也都赶走, 最后放入当前index, 队头就是res[i]的答案.
     * 总结:
     * 每次都先保证queue内元素 index合法,
     * 然后保留大于当前元素的元素, 因为只要没越界, 比当前元素大的 之前的元素 都会保留 除非越界; 一旦出现有比之前元素大的元素时, 会把小于当前元素的 前元素都赶出去.
     * 如果当前value就是最大最, 则会弹出之前的所有元素.
     * 从而保证了queue头的, 肯定是当前最大的元素.
     */
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            //检查队头是否有超过当前范围的index, 有则都提出去
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            //如果当前value 从queue尾对比, 一直弹出, 直到queue有大于当前value的元素.
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            //放入当前index
            q.offer(i);
            //当i位置, 已经满k个元素, 就可以开始填充第一个res元素.
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }
}
