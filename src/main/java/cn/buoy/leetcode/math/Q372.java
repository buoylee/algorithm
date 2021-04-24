package cn.buoy.leetcode.math;

public class Q372 {
    /**
     * https://www.bilibili.com/video/BV1bX4y1G77Z?from=search&seid=10130701434952054965
     * <p>
     * https://leetcode.com/problems/super-pow/discuss/84472/C%2B%2B-Clean-and-Short-Solution
     */
    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length, 1337);
    }

    private int superPow(int a, int[] b, int length, int k) {
        if (length == 1) {
            return powMod(a, b[0], k);
        }
        //(a%k * b%k)%k == (a*b)%k
        //递归, ((前边位数运算)^10%k + 当前运算%k)%k
        return powMod(superPow(a, b, length - 1, k), 10, k) * powMod(a, b[length - 1], k) % k;
    }

    //x^y mod k
    private int powMod(int x, int y, int k) {
        x %= k;
        int pow = 1;
        for (int i = 0; i < y; i++) {
            pow = (pow * x) % k;
        }
        return pow;
    }
}
