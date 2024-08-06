package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q61 {
    /**
     * 看視頻, 然後看註釋. 簡單.
     * https://www.youtube.com/watch?v=CABz3Nd64KQ
     * 思路: 首尾相連, 用偏移次數, 找出 tail node, 那 tail.next 就是 head.
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int listNum = 1;
        ListNode tail = head;
        // find tail and count listNum
        while (tail.next != null) {
            listNum++;
            tail = tail.next;
        }
        // 收尾相连
        tail.next = head;
        // 假如 轉的次數 > list.size, 那麼 newIndex = 2 - 3%2 = 1
        int newHeadIndex = listNum - k % listNum;
        for (int i = 0; i < newHeadIndex; i++)
            tail = tail.next;
        // 更新head
        head = tail.next;
        // 从 tail 和 head 之间, 解除环
        tail.next = null;
        return head;
    }
}
