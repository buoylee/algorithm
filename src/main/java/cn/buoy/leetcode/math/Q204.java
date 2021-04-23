package cn.buoy.leetcode.math;

public class Q204 {
    /**
     * https://www.youtube.com/watch?v=UGeCe5WQNVg
     * <p>
     * 根号看这里, 为什么说 只需要检查到 根号n,
     * 因为: 如果 只有 根号n 是因子, 这个因子 是 只有1个的,
     * 如果出现在 根号n 前边, 这肯定 根号n 后 n之前, 也会有 一个因子.
     * https://leetcode-cn.com/problems/count-primes/solution/kuai-lai-miao-dong-shai-zhi-shu-by-sweetiee/
     */
    public int countPrimes(int n) {
        if (n <= 1) return 0;

        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;

        for (int i = 2; i < Math.sqrt(n); i++) {
            if (!notPrime[i]) {
                for (int j = 2; j * i < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < notPrime.length; i++) {
            if (!notPrime[i]) count++;
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/count-primes/discuss/57588/My-simple-Java-solution
     */
    public int countPrimes2(int n) {
        if (n <= 1) return 0;

        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;

        //为什么是到开方,
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (!notPrime[i]) {
                for (int j = 2; j * i < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < notPrime.length; i++) {
            if (!notPrime[i]) count++;
        }
        return count;
    }
}
