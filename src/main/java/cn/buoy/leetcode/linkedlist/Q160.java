package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q160 {
    /*
    https://www.youtube.com/watch?v=NufEjdUBpgg

    思路: a链 = a' + c,  b链 = b' + c,  c为相交后部分,
    当 a, b 走到null, 马上指向另一链, 相等时即是交点,
    原理 a' + b' + c = b' + a' + c , a走过的路径 = b走过的路径.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}
