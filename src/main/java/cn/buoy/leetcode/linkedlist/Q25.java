package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q25 {

    public static void main(String[] args) {
        Q25 q25 = new Q25();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = new ListNode(7);
        q25.reverseKGroup(listNode, 2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        //为什么需要 k+1, curr 作为 下k个一组 的开头,
        ListNode curr = head;
        int count = 0;
        //看是否满足一轮k数量.
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            //curr = k + 1位
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            //将head移到第k位, 原第2做head
            //不好理解就画图!
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                //作为第k个
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        //统计数量
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next) ;

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        //剩下的大于等于k才可以继续
        for (ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }

            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }
}
