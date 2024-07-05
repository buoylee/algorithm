package cn.buoy.leetcode.random;

import cn.buoy.leetcode.ListNode;

import java.util.Random;

public class Q382 {
    /**
     * 簡單, 懂原理的話. 算法: 水庫抽样. 第一次聽可能不懂, 看完代碼回來看就懂了. 和 Q398 差别不大, 忘记回头看.
     * https://www.youtube.com/watch?v=HOs5eTt2-ZA
     * https://www.youtube.com/watch?v=a4SsHm2fMok 好一點.
     * 每次取新元素 几率 都是当前1/count, 保留原来元素概率为 n-1/n.
     */
    private ListNode head;
    private Random rdm = new Random();

    public Q382(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        ListNode curr = head;
        int num = 0;
        int res = 0;
        while (curr != null) {
            // 1/n 的 n
            num++;
            // 0~num-1 random 取一個
            if (rdm.nextInt(num) == 0)
                res = curr.val;
            curr = curr.next;
        }
        return res;
    }
}
