package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.ListNode;
import cn.buoy.leetcode.TreeNode;

public class Q109 {
    /**
     * 弱智, 108題 + "linklist 找中點", 視頻
     * https://www.youtube.com/watch?v=aH0rBLZLr2E
     * 思路: linklist 找中點, 108題
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) return null;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head, slow);
        thead.right = toBST(slow.next, tail);
        return thead;
    }
}
