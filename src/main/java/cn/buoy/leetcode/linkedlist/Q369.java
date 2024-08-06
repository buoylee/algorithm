package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q369 {
    /**
     * 简单, 视频举的例子不够清晰, 缺少 [1,2,3,9,9,1], 最后一个 1 才是 我们要找的 notEight.
     * https://www.youtube.com/watch?v=6bh8bKxT9o8
     * 思路: 找到最后一个 非9的index(表示后续没有9  或 它就是最后一位 非9, 但后续有 99..), 这个其实就是需要 +1 的index,
     * 如果 notNine 是 tail, 这个就是结果;
     * 如果 notNine 后续还有元素, 那肯定都是9, 只要把后续9都改成0, 就是答案.
     */
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 为了找到最后一个 非9的 index.
        ListNode notNine = dummy;
        while (head != null) {
            if (head.val != 9)
                notNine = head;
            head = head.next;
        }
        // 这里 ++, 有2种情况:
        // 1. 末尾有非9, 不需要进位, 结束.
        // 2. notNine 后边全是 9, 所以 notNine 此位为进位 node(++), 后续"9..."则都改成0; 此外, 例如 "999"的例子, 则是在 dummy.val++
        notNine.val++;
        notNine = notNine.next;
        while (notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }
        // 1. 整个 linklist 都是"999"的情况
        // 2. 除了上例的情况.
        return dummy.val != 0 ? dummy : dummy.next;
    }

    // 跳过
    public ListNode plusOne2(ListNode head) {
        //dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        //j从左到右找最后一个不是9的数,然后i指向其
        //j最后落到链表尾
        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }

        if (j.val != 9) {
            j.val++;
        } else {
            //最后一位 非9 数字 + 1, 后边都是9了, 全置0直到null
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }
        //没进位, 返回原头
        if (dummy.val == 0) {
            return dummy.next;
        }
        //如果最后一个非9元素是dummy, 则返回dummy.
        return dummy;
    }
}
