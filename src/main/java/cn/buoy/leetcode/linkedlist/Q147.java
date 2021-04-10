package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q147 {
    /**
     * https://www.youtube.com/watch?v=N1VVLLan6S0
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return head;
        ListNode prev = new ListNode(0);
        //prev 等于dummy
        prev.next = head;
        ListNode curr = head;
        while (curr.next != null) {
            //next 就是 curr的next, curr是当前检查点
            ListNode next = curr.next;
            //如果next 大于 curr, 检查下一点, 即.next.next
            if (next.val >= curr.val) {
                curr = curr.next;
            } else if (next.val <= prev.next.val) {
                //假如next比第1个有效元素都小, 则放在dummy后
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            } else {
                //如果比第一个有效元素大, 则开始找合适next元素插入的位置, 直到p.next >= 当前检查元素的next, 插入
                ListNode p = prev;
                while (p.next.val < next.val) {
                    p = p.next;
                }
                ListNode pNext = prev.next;
                curr.next = next.next;
                next.next = p.next;
                p.next = next;
            }
        }
        return prev.next;
    }
}
