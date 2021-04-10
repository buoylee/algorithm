package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        //插dummy 做pre
        ListNode FakeHead = new ListNode(0);
        FakeHead.next = head;
        ListNode pre = FakeHead;
        //cur 作 原head
        ListNode cur = head;
        while (cur != null) {
            //只要cur与next相等, 向下保持cur = next
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            //如果 pre.next 和 cur 还是同一个对象, 说明 没有重复.
            //没有重复, 将pre更新到下个元素
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                //否则 直接跳过所有重复值的元素
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return FakeHead.next;
    }
}
