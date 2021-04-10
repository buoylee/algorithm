package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q328 {
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {

            ListNode odd = head, even = head.next, evenHead = even;

            //2, 3存在时:
            while (even != null && even.next != null) {
                //1 -> 3
                odd.next = odd.next.next;
                //2 -> 4
                even.next = even.next.next;
                //odd head指到3
                odd = odd.next;
                //odd head指到4
                even = even.next;
            }
            //偶数index加到奇数index后边
            odd.next = evenHead;
        }
        return head;
    }
}
