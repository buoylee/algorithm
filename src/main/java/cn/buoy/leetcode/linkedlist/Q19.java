package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q19 {
    /**
     * 簡單, 看視頻.
     * https://www.youtube.com/watch?v=zSTt-x8JeFI
     * 思路: 快慢指針, fast 比 slow 快 n 步, 當 fast 到達 end 時, slow 就是 target 元素.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //插个dummy
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy, fast = dummy;
        // dummy.next 指向 head
        slow.next = head;
        // 細節: 因爲 下邊 slow/fast 都多走了一步, 所以 這裏間隔也需要多1.
        for (int i = 1; i <= n + 1; i++)
            fast = fast.next;
        // Move fast to the end, maintaining the gap
        // 細節: 注意這裏結束時, fast 多走了一步(末尾元素後一位).
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // Skip the desired node 跳过要删除的node
        // 刪除目標
        slow.next = slow.next.next;
        // 跳掉dummy
        return dummy.next;
    }
}
