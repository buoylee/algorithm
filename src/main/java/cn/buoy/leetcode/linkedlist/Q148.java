package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q148 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=xUFYCXsncm0 这个短
     * 思路: 典型 merge sort
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode middle = getMiddle(head);
        // 3->7->5->1
        // 3->7  5->1
        // 3 7  5 1
        ListNode next = middle.next;
        middle.next = null;
        // 递归调用sortList
        // 直到只剩下一个元素的时候开始merge
        // 然后返回结果
        return merge(sortList(head), sortList(next));
    }

    /**
     * 取中点, 注意边界.
     */
    private ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 细节: 中点位置: while (fast != null && fast.next != null) // 奇数: 中点; 偶数: 后点
        while (fast.next != null && fast.next.next != null) { // 奇数: 中点; 偶数: 前点
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 归并排序 merge 阶段
     */
    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        // 选小的插入
        while (a != null && b != null) {
            if (a.val <= b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        // 当 其一 link == null, 就把 另一 link 剩余node 插入最后.
        if (a == null)
            cur.next = b;
        else
            cur.next = a;
        return dummy.next;
    }

    /**
     * https://www.youtube.com/watch?v=M1TwY0nsTZA
     * 这个看起来代码不够简洁
     */
    public ListNode sortList2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int n = 0;
        //统计元素个数
        while (head != null) {
            head = head.next;
            n++;
        }

        for (int step = 1; step < n; step <<= 1) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                prev = merge2(left, right, prev);
            }
        }

        return dummy.next;
    }

    /**
     * Divide the linked list into two lists,
     * while the first list contains first n nodes
     * return the second list's head
     */
    private ListNode split(ListNode head, int step) {
        if (head == null) return null;

        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }

        ListNode right = head.next;
        //这里切开了, 为了merge好判断, 上一步保留了next信息, 并返回.
        head.next = null;
        return right;
    }

    private ListNode merge2(ListNode left, ListNode right, ListNode prev) {
        //这里吧断开的连回来.同一个step 的前一部分 和 现在 l, r 链表 连起来.
        ListNode cur = prev;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        if (left != null)
            cur.next = left;
        else if (right != null)
            cur.next = right;

        while (cur.next != null)
            cur = cur.next;
        return cur;
    }

    //-----------------------------------------------------------------------------

    /*
    自上而下, 空间复杂度为logn
     */
    public ListNode sortList3(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList2(head);
        ListNode l2 = sortList2(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    ListNode merge3(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0), p = l;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return l.next;
    }

    public static void main(String[] args) {
        Q148 q148 = new Q148();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = new ListNode(7);
        q148.sortList2(listNode);
    }
}
