package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        head.next = deleteDuplicates(head.next);
        //删除前一个的相同元素(跳过前一个).
        return head.val == head.next.val ? head.next : head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode list = head;

        while (list != null) {
            if (list.next == null) {
                break;
            }
            if (list.val == list.next.val) {
                list.next = list.next.next;
            } else {
                list = list.next;
            }
        }

        return head;
    }
}
