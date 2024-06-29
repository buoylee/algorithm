package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q160 {
    /**
     * https://www.youtube.com/watch?v=NufEjdUBpgg
     * 简单,
     * 思路: a链 = a' + c,  b链 = b' + c,  c为相交后部分,
     * 当 a, b 走到null, 马上指向另一链, 相等时即是交点,
     * 原理 a' + b' + c = b' + a' + c , a走过的路径 = b走过的路径.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if (headA == null || headB == null) return null;

        ListNode currA = headA;
        ListNode currB = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        // 关键: 当 A/B 都走过了 3段长度, 就是 焦点, 画图好理解.
        while (currA != currB) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            currA = currA == null ? headB : currA.next;
            currB = currB == null ? headA : currB.next;
        }

        return currA;
    }
}
