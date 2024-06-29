package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q142 {
    /**
     * 原理:
     * https://www.youtube.com/watch?v=UkKBPGt5Nok
     * https://www.youtube.com/watch?v=kZP8Cij1fxk 遠離也可以參考這個.
     * 相遇的时候, 将一点回位, 以相同速度前进, 相遇时就是相交点, m = x + (k-1)n, m: head到交点距离, k: 快指针多绕圈数, n: 一圈长度.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 找到相遇點
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        // 不存在 相遇點, 返回 null
        if (fast == null || fast.next == null) return null;
        // 讓一點 開始從 head, 另一點 開始從 相遇點 速度相同開始走, 相遇就是 result.
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    // 思路一樣, 這個代碼沒上邊清晰. 可以不看.
    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head, fast = head, start = head;
        //不等或者next不为null就继续绕.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //相遇时
            if (slow == fast) {
                //开始head 和 相遇点继续走, 直到再次相遇.
                while (slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }
        }
        return null;
    }
}
