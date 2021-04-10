package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q143 {
    public static void main(String[] args) {
        Q143 q143 = new Q143();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = new ListNode(7);
        q143.reorderList(listNode);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        //Find the middle of the list
        ListNode p1 = head;
        ListNode p2 = head;
        //走完这步, p1到了需要反转的后半部head.
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle = p1;
        ListNode preCurrent = p1.next;
        //吧preCurrent后的元素移到middle后边, 直到preCurrent 后为null.
        while (preCurrent.next != null) {
            //cur = 5
            ListNode current = preCurrent.next;
            //4 -> 6
            preCurrent.next = current.next;
            //5 -> 4
            current.next = preMiddle.next;
            //3 -> 5
            preMiddle.next = current;
        }

        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1 = head;
        p2 = preMiddle.next;
        while (p1 != preMiddle) {
            preMiddle.next = p2.next;
            //6->2
            p2.next = p1.next;
            //1->6
            p1.next = p2;
            //p1, p2向后平移2位
            p1 = p2.next;
            p2 = preMiddle.next;
        }
    }

}
