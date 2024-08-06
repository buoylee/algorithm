package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q206 {
    /**
     * 超简单, 最普通的 reverse 数组. 记忆一个公式写法.
     * https://www.youtube.com/watch?v=hT2zDY4nm0I
     * 思路: 把 originalHead 放在 newHead 头部,
     * 然后 originalHead 指针移动到 originalHeadNext, newHead 指针移动到 新加入的 originalHead,
     * 如此循环到 originalHead == null.
     */
    public ListNode reverseList(ListNode originalHead) {
        if (originalHead == null || originalHead.next == null)
            return originalHead;
        ListNode newHead = null;
        while (originalHead != null) {
            // 先保存 originalHeadNext, 因为下一步 originalHead 马上连接 newHead, 如果不保存将会丢失 originalHeadNext.
            ListNode originalHeadNext = originalHead.next;
            // originalHead 连接 newHead
            originalHead.next = newHead;
            // newHead 指针 移动到 新加入的 originalHead.
            newHead = originalHead;
            // originalHead指针 移动到 originalHeadNext
            originalHead = originalHeadNext;
        }
        return newHead;
    }
}
