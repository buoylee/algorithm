package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

import java.util.PriorityQueue;

public class Q23 {
    /**
     * 超簡單, 看.
     * https://www.youtube.com/watch?v=Jq6QWstM66s
     * 思路: 直接用一個 PriorityQueue, k個 link head 入队, pop 出最小那個後, 在 push 他的 next 入隊, 如此循環直到 k個 node 都爲空.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        // k個 link 的 head 全部放入 queue
        for (ListNode node : lists)
            if (node != null)
                queue.add(node);
        while (!queue.isEmpty()) {
            // 弹出的 link head 还有 next, 就继续放回队列中.
            curr.next = queue.poll();
            curr = curr.next;
            if (curr.next != null)
                queue.add(curr.next);
        }
        return dummy.next;
    }

    /**
     * 递归
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        return partion(lists, 0, lists.length - 1);
    }

    public static ListNode partion(ListNode[] lists, int s, int e) {
        if (s == e) return lists[s];
        if (s < e) {
            int q = (s + e) / 2;
            ListNode l1 = partion(lists, s, q);
            ListNode l2 = partion(lists, q + 1, e);
            return merge(l1, l2);
        } else
            return null;
    }

    //This function is from Merge Two Sorted Lists.
    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
