package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q328 {
    /**
     * 简单, 视频.
     * https://www.youtube.com/watch?v=YSU9ybcZspE
     * 思路: 分别归类到 odd/even 2个 linklist 中, 然后 odd tail 连接 even head.
     */
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode oddCurr = head;
            ListNode evenCurr = head.next;
            // 用于 分类结束后, "odd 尾" 快速连接 evenHead
            ListNode evenHead = evenCurr;
            while (evenCurr != null && evenCurr.next != null) {
                // 关键: 跳一个 连接 就是 对应的 奇偶.
                oddCurr.next = oddCurr.next.next;
                evenCurr.next = evenCurr.next.next;
                // curr 指针移动到 刚完成的 next.
                oddCurr = oddCurr.next;
                evenCurr = evenCurr.next;
            }
            //奇数 index 指向 evenHead
            oddCurr.next = evenHead;
        }
        return head;
    }
}
