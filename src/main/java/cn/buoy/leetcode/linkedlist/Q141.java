package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q141 {
    /**
     * 視頻, 過
     * https://www.youtube.com/watch?v=bxCb37nLXWM
     * 思路: 2個人跑操場, 一快一慢, 一定會有相遇的時候.
     */
    // 如果有环, 快满指针都在环中前进时, 则快指针会一步一步赶上慢指针, 最后相遇.
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
