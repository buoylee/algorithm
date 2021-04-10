package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q203 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        head.next = removeElements(head.next, val);
        //删除即直接取next
        return head.val == val ? head.next : head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curr = head, prev = fakeHead;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            //通过前一个元素, 插入dummy, 防null
            curr = curr.next;
        }
        return fakeHead.next;
    }
}
