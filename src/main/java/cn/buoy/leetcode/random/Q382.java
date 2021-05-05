package cn.buoy.leetcode.random;

import cn.buoy.leetcode.ListNode;

import java.util.Random;

public class Q382 {
    /**
     * https://www.youtube.com/watch?v=HOs5eTt2-ZA
     * 和 Q398 差别不大, 忘记回头看.
     * 每次取新元素 几率 都是当前1/count, 保留原来元素概率为 n-1/n.
     */
    private ListNode head;
    private Random r = new Random();


    public Q382(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        ListNode now = head;
        int n = 0;
        int res = 0;
        while (now != null) {
            //计算同一个target的个数
            n++;
            //我们以同一个数字的频数1/n的概率选出其中一个索引
            if (r.nextInt(n) == 0)
                res = now.val;
            now = now.next;
        }
        return res;
    }
}
