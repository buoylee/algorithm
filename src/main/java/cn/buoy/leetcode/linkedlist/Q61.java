package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int listNum = 1;
        ListNode tail = head;

        //find tail and count listNum
        while (tail.next != null) {
            listNum++;
            tail = tail.next;
        }
        //收尾相连
        tail.next = head;
        //加入 有2个元素, 要转3次, 1 -> 2, 2 -> 1, 1 -> 2, 2 -> 1, newIndex = 2 - 3%2 = 1
        int newHeadIndex = listNum - k % listNum;
        //上例, 所以需要移一位
        for (int i = 0; i < newHeadIndex; i++) {
            tail = tail.next;
        }
        //更新head
        head = tail.next;
        //解除环
        tail.next = null;

        return head;
    }
}
