package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q234 {
    public static void main(String[] args) {
        Q234 q234 = new Q234();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);
//        listNode.next.next.next = new ListNode(1);
//        q234.isPalindrome(listNode);
        System.out.println(q234.isPalindrome(listNode));
    }

    public boolean isPalindrome(ListNode head) {
        //快慢指针 2x
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果是fast 还有数, 说明是奇数个
        // 1 1 X 1; 1 1 X
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        //反转slow子链表, fast置head, 开始对比, 因为总是slow先结束, 不用管奇数的中位数.
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
