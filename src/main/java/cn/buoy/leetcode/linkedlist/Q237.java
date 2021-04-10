package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q237 {
    /*
    看来智障
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
