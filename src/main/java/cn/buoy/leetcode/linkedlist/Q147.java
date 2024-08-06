package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q147 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=N1VVLLan6S0
     * 思路: 從頭檢查, 找到一個 next 比 curr 小的 temp node,
     * 再次從頭找到 比這個 temp node 大的 pre.next, 然後 在 pre 和 pre.next 之間 插入 temp node,
     * 注意, 每发现一个位置不对的 node, 都要从头开始找要插入的地方.
     * 直到curr.next == null.
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 檢查過的最後一位(用來比較 cur 和 cur.next, 如果 cur > cur.next, 那 cur.next 就要移動位置).
        ListNode cur = head;
        // 應該插入位置的 prev
        ListNode prev = null;
        // 需要移動的 node
        ListNode temp = null;
        while (cur != null && cur.next != null) {
            // 位置正確
            if (cur.val <= cur.next.val) {
                cur = cur.next;
            } else {
                // 要移動的 node
                temp = cur.next;
                // 刪除要移動的 node
                cur.next = cur.next.next;
                // 每次都要从头开始找
                prev = dummy;
                // 找到插入的位置
                while (prev.next.val <= temp.val)
                    prev = prev.next;
                // 插入
                temp.next = prev.next;
                prev.next = temp;
            }
        }
        return dummy.next;
    }

    // 不夠簡潔
    public ListNode insertionSortList2(ListNode head) {
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
