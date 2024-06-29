package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q2 {
    /**
     * https://www.youtube.com/watch?v=SeBLjY58iY8
     * 簡單到爆, 直接看視頻
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 爲了方便取結果, 保留dummy指針.
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        // 進位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;
            //餘數(加完後, curr 的值)
            curr.next = new ListNode(sum % 10);
            // 進位
            carry = sum / 10;

            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        // 最後一位如果要進位, 記得+個 ListNode(1).
        if (carry != 0) {
            curr.next = new ListNode(carry);
        }
        return dummy.next;
    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 爲了方便取結果, 保留dummy指針.
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int sum = 0;
        //当2链表当前node都没有时, 跳出
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        // 最後一位如果要進位, 記得+個 ListNode(1).
        if (sum / 10 == 1)
            curr.next = new ListNode(1);
        return dummy.next;
    }


    /*
    相对好读懂
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
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
