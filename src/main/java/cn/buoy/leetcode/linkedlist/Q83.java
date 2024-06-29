package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q83 {

    /**
     * https://www.youtube.com/watch?v=gO4mJZrye6w
     * 簡單, 聽下思路, 下邊代碼更直觀.
     * 思路: 比較 curr 和 curr.next,
     * 相同, 就刪掉 curr.next, 即 (curr.next = curr.next.next);
     * 不同, curr 就移動到 curr.next, 即(curr = curr.next) 繼續檢查.
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 用於檢查的指針.
        ListNode curr = head;
        while (curr != null) {
            if (curr.next == null) {
                break;
            }
            // curr 和 curr.next 的 value 是否相同, 是的話, 刪除 curr.next
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else { // 不相同, curr 就跳到 curr.next
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     * 不夠直觀, 值得思考.
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        head.next = deleteDuplicates(head.next);
        //删除前一个的相同元素(跳过前一个).
        return head.val == head.next.val ? head.next : head;
    }
}
