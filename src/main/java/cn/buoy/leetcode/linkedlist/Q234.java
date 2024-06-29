package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q234 {
    /**
     * 超简单, 视频, 代码注意细节.
     * https://www.youtube.com/watch?v=GTKm1PrYjwo
     * 思路: 无论是奇数/偶数, 我们取出对称的后半段(奇数的话, 不算中点), 将其reverse, 然后开始对比node,
     * 直到 midNext == null.
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;
        // 细节: 这里取的是 后边段 head 的前一个 node.
        ListNode mid = getMid(head);
        ListNode midNext = reverse(mid.next);
        // 逐一对比对称点.
        while (midNext != null) {
            if (head.val == midNext.val) {
                head = head.next;
                midNext = midNext.next;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 统一用 while(fast.next != null && fast.next.next != null) 这种写法好了, 奇: 中点; 偶: 前一点
     */
    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 最普通的linklist翻转
     */
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    /**
     * 代码不够优, 跳过
     */
    public boolean isPalindrome2(ListNode head) {
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

    public ListNode reverse2(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Q234 q234 = new Q234();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);
//        listNode.next.next.next = new ListNode(1);
//        q234.isPalindrome(listNode);
        System.out.println(q234.isPalindrome(listNode));
    }
}
