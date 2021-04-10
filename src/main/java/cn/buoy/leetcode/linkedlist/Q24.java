package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q24 {
    /*
    第一个和第2个换位,
    跟新后, 原第1个元素指向递归的剩余链表, 原第2个元素指向原第1个元素.
     */
    public ListNode swapPairs(ListNode head) {
        if ((head == null) || (head.next == null))
            return head;
        ListNode next = head.next;
        head.next = swapPairs(head.next.next);
        next.next = head;
        return next;
    }

    public ListNode swapPairs2(ListNode head) {
        //加入无用head节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            //原第1, 2都指向3
            first.next = second.next;
            //dummy指原2,
            current.next = second;
            //原2指原1,
            current.next.next = first;
            //平移2位继续到尾
            current = current.next.next;
        }
        return dummy.next;
    }
}
