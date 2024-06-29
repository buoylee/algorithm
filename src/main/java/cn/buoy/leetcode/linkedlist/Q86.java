package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

/**
 * https://www.youtube.com/watch?v=72fBK4q0qxo
 * 超簡單, 視頻.
 * 思路: 分2條 link, 一條 大於 x, 一條 小於等於 x,
 * 然後 smallerCurr 指向 biggerDummy.next,
 * 最後 返回 smallerDummy.next.
 */
public class Q86 {
    public ListNode partition(ListNode head, int x) {
        ListNode smallerDummy = new ListNode(0);
        ListNode biggerDummy = new ListNode(0);
        ListNode smallerCurr = smallerDummy;
        ListNode biggerCurr = biggerDummy;
        while (head != null) {
            if (head.val < x) {
                // smallerCurr 指向 head, 然後更新 smallerCurr = smallerCurr.next
                smallerCurr = smallerCurr.next = head;
            } else { // x = 3 放右邊
                biggerCurr = biggerCurr.next = head;
            }
            // head 移到 下一點(head.next)
            head = head.next;
        }
        // no need for extra check because of fake heads
        smallerCurr.next = biggerDummy.next; // join bigger after smaller
        biggerCurr.next = null; // cut off anything after bigger
        return smallerDummy.next;
    }

    // 先不用看, 第1解夠好了
    public ListNode partition2(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head != null) {
            if (head.val < x) {
                curr1.next = head;
                curr1 = head;
            } else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}
