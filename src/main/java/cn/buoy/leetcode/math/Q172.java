package cn.buoy.leetcode.math;

public class Q172 {
    /**
     * https://www.youtube.com/watch?v=88mG_huCxt0
     * 阶乘 2肯定比5多, 关键在于找几个5,
     * 每次5的阶乘都会多比上一个阶乘多1个5
     * 每除一次, 就说明有几个5的倍数, 同时也表示 剩下除以5后的倍数最大是几, 这里还有包含有 5的倍数, 例如 5*5 =25, 125. 一直除了 再也没有5的倍数后结束.
     */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n != 0) {
            int tmp = n / 5;
            count += tmp;
            n = tmp;
        }
        return count;
    }
}
