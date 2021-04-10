package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q141 {
    /*
    https://www.youtube.com/watch?v=bxCb37nLXWM
     */

    //如果有环, 快满指针都在还中时, 则快指针会一步一步赶上满指针, 最后重叠.
    public boolean hasCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }
        return false;
    }
}
