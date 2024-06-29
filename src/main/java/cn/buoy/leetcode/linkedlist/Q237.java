package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q237 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=x3OmPfSs6jo
     * 思路: 使当前待删 node, 成为 node.next.
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
