package cn.buoy.leetcode.linkedlist;

import cn.buoy.leetcode.ListNode;

public class Q143 {
    /**
     * 簡單, 視頻, 註釋.
     * https://www.youtube.com/watch?v=h-lJwhlSzNo
     * 思路: fast == 2*slow, 當 fast 停止, slow 就在 偶數arr 後一點, 奇數arr 中點,
     * 爲什麼把 中點放在 後半部,
     * 這個時候, 如果 arr.size 是奇數, 只要遍歷到 前半部分 == null 時, 就可以結束(偶數當然也結束), 因爲最後一個(中點)已經在 後半部 arr 的末尾.
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // 用於保存 前半段的最後 node, 需要將此 node.next == null, 爲了在最後合併 前/後2部分linklist時, 當到達 前半部 末尾時, 停止.
        ListNode temp = null;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            // 真正的 slow 的前一個 node, 是前半部的末點.
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 斷開與 後半部的 head(當前 slow)的連接, 爲了 最後的 merge 終止條件.
        temp.next = null;
        // 反轉後半link
        ListNode l2 = reverse(slow);
        merge(head, l2);
    }

    /**
     * 其實這裏翻轉, 使用了 2個 linklist,
     * 1. head =>...;
     * 2. ... => newLinkHead;
     * 不斷把 originalHead 從第一個link 插到 第2個link 的頭部,
     * 完成後, 再把 originalHead指針 移動到 第1link 的 head.next, pre指針 移動到 第2link 頭部.
     */
    private ListNode reverse(ListNode originalHead) {
        ListNode newLinkHead = null;
        // [1,2,3,4]
        while (originalHead != null) {
            //next = [2,3,4]
            ListNode originalHeadNext = originalHead.next;
            // 1 => newLinkHead(null)
            originalHead.next = newLinkHead;
            // newLinkHead = 1
            newLinkHead = originalHead;
            // head = [2,3,4]
            originalHead = originalHeadNext;
        }
        return newLinkHead;
    }

    private void merge(ListNode curr1, ListNode curr2) {
        while (true) {
            ListNode next1 = curr1.next;
            ListNode next2 = curr2.next;
            curr1.next = curr2;
            // 因爲中點在 右半邊的原因, 只要 n1 == null 就可以結束.
            if (next1 == null) return;
            curr2.next = next1;
            curr1 = next1;
            curr2 = next2;
        }
    }

    // 這個不夠清晰, 先跳過
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;

        //Find the middle of the list
        ListNode p1 = head;
        ListNode p2 = head;
        //走完这步, p1到了需要反转的后半部head.
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle = p1;
        ListNode preCurrent = p1.next;
        //吧preCurrent后的元素移到middle后边, 直到preCurrent 后为null.
        while (preCurrent.next != null) {
            //cur = 5
            ListNode current = preCurrent.next;
            //4 -> 6
            preCurrent.next = current.next;
            //5 -> 4
            current.next = preMiddle.next;
            //3 -> 5
            preMiddle.next = current;
        }

        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1 = head;
        p2 = preMiddle.next;
        while (p1 != preMiddle) {
            preMiddle.next = p2.next;
            //6->2
            p2.next = p1.next;
            //1->6
            p1.next = p2;
            //p1, p2向后平移2位
            p1 = p2.next;
            p2 = preMiddle.next;
        }
    }

    public static void main(String[] args) {
        Q143 q143 = new Q143();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = new ListNode(7);
        q143.reorderList(listNode);
    }

}
