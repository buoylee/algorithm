package cn.buoy.leetcode.math;

public class Q172 {
    /**
     * 看視頻, 找規律.
     * https://www.youtube.com/watch?v=88mG_huCxt0
     * 思路: 2數相乘得10, 肯定需要 2 和 5, 因爲看規律下來, 2總比5多, 所以, 問題轉化爲 !n 中有幾個 5 相乘.
     * 5^N, 就會多增加一個5(25, 125...), 每5次又會增加一個5
     * 每除一次5, 都會使 發現和上一步的數字一樣, 繼續/5, 把數字寫下來會比較容易看出規律.
     */
    public int trailingZeroes(int n) {
        if (n == 0) return 0;
        return n / 5 + (trailingZeroes(n / 5));
    }

    public int trailingZeroes2(int n) {
        int count = 0;
        while (n != 0) {
            int tmp = n / 5;
            count += tmp;
            n = tmp;
        }
        return count;
    }
}
