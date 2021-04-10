package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q369 {
    /*
    画图就懂了, 简单!
     */
    public ListNode plusOne(ListNode head) {
        //dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        //j从左到右找最后一个不是9的数,然后i指向其
        //j最后落到链表尾
        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }

        if (j.val != 9) {
            j.val++;
        } else {
            //最后一位 非9 数字 + 1, 后边都是9了, 全置0直到null
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }
        //没进位, 返回原头
        if (dummy.val == 0) {
            return dummy.next;
        }
        //如果最后一个非9元素是dummy, 则返回dummy.
        return dummy;
    }
}
