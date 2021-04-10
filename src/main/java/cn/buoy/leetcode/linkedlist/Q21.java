package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q21 {
    /*
    递归方式, 好好看下.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /*
    迭代没什么好说的
     */
}
