package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q24 {
    /**
     * https://www.youtube.com/watch?v=OJzYvbHX1G8
     * 思路: 連續的2個元素換位, 直到最後. 原第1个元素指向递归的剩余链表, 原第2个元素指向原第1个元素.
     * 2種都可以, 第1種寫法比較快, 第2種步驟清晰.
     */

    /**
     * 用於交換 原 head 與 原 head.next 的位置, 的遞歸函數. 使得 原 head.next 成爲 新 head
     */
    public ListNode swapPairs(ListNode head) {
        if ((head == null) || (head.next == null))
            return head;
        // 記錄 原 head.next
        ListNode next = head.next;
        // 交換 原 head.next 和 原 head 的 位置:
        // 1. 原 head 指向 原 head.next.next swapPairs後的 新head.
        head.next = swapPairs(head.next.next);
        // 2. 原 head.next 指向 原 head.
        next.next = head;
        return next;
    }

    /**
     * 這個比較直觀, 一步步交換清楚寫出來.
     * 總是交換 dummy 後面2個 node 的位置.
     */
    public ListNode swapPairs2(ListNode head) {
        // dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            //細節: 原第1, 2都指向3, 這裏先用 first 指向 後續還沒開始換位的node(second.next), 這樣就不用變量先把他存起來.
            first.next = second.next;
            //dummy指原2,
            current.next = second;
            //原2指原1,
            current.next.next = first;
            //平移2位继续到尾
            current = current.next.next;
        }
        return dummy.next;
    }
}
