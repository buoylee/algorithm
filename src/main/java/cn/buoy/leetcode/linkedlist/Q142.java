package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q142 {
    /**
     * 原理:
     * https://www.youtube.com/watch?v=UkKBPGt5Nok
     * 相遇的时候, 将一点回位, 以相同速度前进, 相遇时就是相交点, m = x + (k-1)n, m: head到交点距离, k: 快指针多绕圈数, n: 一圈长度.
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head, fast = head, start = head;
        //不等或者next不为null就继续绕.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //相遇时
            if (slow == fast) {
                //开始head 和 相遇点继续走, 直到再次相遇.
                while (slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }
        }
        return null;
    }
}
