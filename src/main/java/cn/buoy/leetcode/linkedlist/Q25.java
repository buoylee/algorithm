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

    /**
     * https://www.youtube.com/watch?v=DryIN7iL4pA
     * 很像 24, 其實是簡單的. 視頻快速看, 然後看註釋就好.
     * 思路: 翻轉 linklist 有多種方式, 分組k. 然後就是一些邊界問題要想清楚.
     */
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
            // 這裏做法是, [1,2,3,4,5,6], k = 4
            // [2,3,4,1,5,6] => [3,4,2,1,5,6] => [4,3,2,1,5,6]...
            // 這裏其實 4- 步就能完成, 不過 這裏多做一步, 結果還是一樣.
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
            // 這裏做法是, [1,2,3,4,5,6], k = 4
            // [0,2,1,3,4,5,6] => [0,3,2,1,4,5,6] => [0,4,3,2,1,5,6]...
            // 不斷的把, tail.next(1後邊的數),放在 dummy 之後; 然後 tail 指向 tail.next.next
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
