package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        //直到pre作为left的pre
        for (int i = 0; i < left - 1; i++)
            pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 - 3 - 4 - 5 ; left=2; right =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        //如果有3个元素需要反, 实际操作2次即可, n - 1 次.
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;

    }
}
