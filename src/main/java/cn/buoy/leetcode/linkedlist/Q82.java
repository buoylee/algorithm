package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q82 {
    /**
     * 可以复习
     * 簡單, 视频, 註釋.
     * https://www.youtube.com/watch?v=w16pq8_DVno
     * 思路: 因爲需要跳過所有重複的元素(包含自身).
     * 需要 1個指針 curr, curr這個index以及之前的node都是檢查過的, 去重過的.
     * 接下來, 我們檢查 curr 的 next 和 next.next, 先記錄 next 的 value, 然後 比較 next 是否 == next.next,
     * 是就 curr.next = curr.next.next(跳過curr後邊一個 node);
     * 不是就把 curr 指針移動到 curr = curr.next;
     * 如此循環到 curr.next.next == null.
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        // 只要 curr 後續 '只有一個 node' 或 '沒有 node', 直接返回 dummy.next
        while (curr.next != null && curr.next.next != null) {
            if (curr.next.val == curr.next.next.val) {
                // 关键: 記錄 已重複的 val, 用於後續判斷
                int val = curr.next.val;
                // 如果 後續 node 還是 == val, 繼續跳過
                // 這裏是爲了代碼簡潔, 對於每次開始 下邊while, 第一次 下邊的判斷都是true.
                // 删除 所有 和 val 重复的 ele, 一个都不剩.
                while (curr.next != null && curr.next.val == val)
                    curr.next = curr.next.next;
            } else
                // 关键: 和 83 题不同点. 指針curr 移動到已完成檢查的 node. 只有在 第一次比较 next 和 next.next 时, 才有可能保留一个 ele, 一旦出现重复, 就会在上边全部删除
                curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * 不夠直接, 可以放棄. 其實可以只用一個 curr 指針.
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null)
            return null;
        //插dummy 做pre
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
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
        return dummy.next;
    }
}
