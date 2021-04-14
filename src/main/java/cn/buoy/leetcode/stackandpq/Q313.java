package cn.buoy.leetcode.stackandpq;

import java.util.Arrays;

public class Q313 {
    /**
     * 看264
     * https://www.youtube.com/watch?v=ZG86C_U-vRg
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
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
}
