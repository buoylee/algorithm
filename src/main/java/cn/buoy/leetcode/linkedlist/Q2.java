package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q2 {
    /*
    2种方法都差不多
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        //当2链表当前node都没有时, 跳出
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }


    /*
    相对好读懂
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int left = 0;
        ListNode dummy = new ListNode(0), tail = dummy;

        // iterate two list, add each position until 2 lists are finished && left equals to 0
        while (!(l1 == null && l2 == null && left == 0)) {
            // is number1 finished?
            int add1 = l1 != null ? l1.val : 0;
            // is number2 finished?
            int add2 = l2 != null ? l2.val : 0;
            int sum = add1 + add2 + left;
            left = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            tail.next = newNode;
            tail = newNode;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}
