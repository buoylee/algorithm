package cn.buoy.leetcode.stackandpq;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q313 {
    /**
     * 不好理解
     * https://www.youtube.com/watch?v=LqU1XR9Khdg 這個講的超清楚
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        PriorityQueue<Num> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        // 第一次的 candidate
        for (int i = 0; i < primes.length; i++)
            pq.add(new Num(primes[i], 1, primes[i]));
        for (int i = 1; i < n; i++) {
            // 直接插入 最小的 ugly
            res[i] = pq.peek().val;
            // ugly 重複則, prime 都需要 ++
            while (pq.peek().val == res[i]) {
                Num curr = pq.poll();
                // 關鍵: 第一次 進入這裏時(不重複), 其實就是那 最後一個 ugly * prime
                pq.add(new Num(curr.prime * res[curr.index], curr.index + 1, curr.prime));
            }
        }
        return res[n - 1];
    }

    class Num {
        int val; //
        int index; // 這是某個質數列內的 index, 有幾個 這個某個質數 相乘?
        int prime;

        public Num(int val, int index, int prime) {
            this.val = val;
            this.index = index;
            this.prime = prime;
        }
    }

    /**
     * 思路一樣
     * https://www.youtube.com/watch?v=ZG86C_U-vRg
     */
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] ugly = new int[n];
        //保存各个质数用了几次
        int[] idx = new int[primes.length];
        //更新各个质数, 更新的下一个值. 看视频.
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);
        int next = 1;
        //n 是 第几个丑数
        for (int i = 0; i < n; i++) {
            //在上个循环中选出最小的, 作为第i个丑数
            ugly[i] = next;
            next = Integer.MAX_VALUE;
            //j 是第几个质数
            for (int j = 0; j < primes.length; j++) {
                //skip duplicate and avoid extra multiplication
                //如果当前[i] 刚好等于该质数的当前出现的值, 则更新之下一个值, 即 [i] = ugly[使用了这个质数的次数] * 这个质数.
                if (val[j] == ugly[i])
                    val[j] = ugly[idx[j]++] * primes[j];
                //find next ugly number
                next = Math.min(next, val[j]);
            }
        }
        return ugly[n - 1];
    }

    /**
     * 和 264 一模一樣的思路, 不好理解, 需要複習
     * https://www.youtube.com/watch?v=KTaAYX4Ps_0
     * 思路:
     */
    public int nthSuperUglyNumber3(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        PriorityQueue<int[]> heap = new PriorityQueue<>();
        for (int prime : primes)
            heap.add(new int[]{prime, prime, 0});

        // 找第i個 最小的 uglyNumbers
        for (int i = 1; i < n; i++) {
            uglyNumbers[i] = heap.peek()[0];
            while (heap.peek()[0] == uglyNumbers[i]) {
                int[] curr = heap.poll();
                // 關鍵: 理解這一步
                heap.add(new int[]{curr[1] * uglyNumbers[curr[2] + 1],
                        curr[1], // 這個 prime
                        curr[2] + 1});
            }
        }
        return uglyNumbers[n - 1];
    }
}
