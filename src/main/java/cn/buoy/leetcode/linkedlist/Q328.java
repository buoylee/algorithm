package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q328 {
    /**
     * https://www.youtube.com/watch?v=YSU9ybcZspE
     * 简单, 视频.
     * 思路: 分别归类到 odd/even 2个 linklist 中, 然后 odd tail 连接 even head.
     */
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode oddCurr = head;
            ListNode evenCurr = head.next;
            ListNode evenHead = evenCurr;

            while (evenCurr != null && evenCurr.next != null) {
                // 1,2,3,4,5
                // 关键: 这里很巧妙, 不需要用到 temp 来过渡.
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
