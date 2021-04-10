package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q206 {

    //简单, 自己画下图!
    public ListNode reverseList(ListNode head) {
        /* iterative solution */
        ListNode newHead = null;
        while (head != null) {
            //拿出原链表的head next
            ListNode next = head.next;
            //将原head next 设置为 null
            head.next = newHead;
            //新头原头设为原链表的head next
            newHead = head;
            head = next;
        }
        return newHead;
    }
}
