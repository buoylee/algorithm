package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q21 {
    /**
     * 簡單, 看視頻.
     * https://www.youtube.com/watch?v=naUO9liCm4s
     * 思路: 不斷比較 頭部元素 較小的, 插入 curr node.next.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) { // 2 link 还都有 node 存在比较时
            // 哪個小, 就連接誰, 然後next.
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        // 當有其一爲空, 不再需要比較, 直接連接 不爲空的 node.
        if (l1 != null)
            curr.next = l1;
        if (l2 != null)
            curr.next = l2;
        return dummy.next;
    }


    // 沒必要, 不直觀.
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /*
    迭代没什么好说的
     */
}
