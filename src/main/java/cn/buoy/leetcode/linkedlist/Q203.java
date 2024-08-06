package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q203 {
    /**
     * 超简单, 视频
     * https://www.youtube.com/watch?v=l4OAzNyx9vw
     * 思路: 查看 curr 的 next 是否为 val,
     * 是则, 跳过next, curr.next = curr.next.next;
     * 否则, 指针移到 next, curr = curr.next;
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else
                curr = curr.next;
        }
        return dummy.next;
    }

    // 递归, 检查第一个, 不是最直观.
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null)
            return null;
        head.next = removeElements(head.next, val);
        //删除即直接取next
        return head.val == val ? head.next : head;
    }

    // 代码不够简洁, 跳过.
    public ListNode removeElements3(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head, prev = dummy;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            //通过前一个元素, 插入dummy, 防null
            curr = curr.next;
        }
        return dummy.next;
    }
}
