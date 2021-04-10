package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        //插个dummy
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        //Move fast in front so that the gap between slow and fast becomes n
        //倒数第n个, [-1, 0, 1, 2, 3, 4, 5], 假如倒2, j要到1然后同步移动到 j == null,  3 j = 2, 因为判断条件为j等于null, 所以要移到末尾后一位. 如果要删除这位 就要找到 n - 1 的元素才能删. (如果到倒2, 则机要比slow(1) + 3)
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node 跳过要删除的node
        slow.next = slow.next.next;
        //跳掉dummy
        return start.next;
    }
}
