package cn.buoy.leetcode.math;

public class Q231 {
    /**
     * 簡單, 有思路的話, 視頻
     * https://www.youtube.com/watch?v=8qy2W5xNgZk
     * 思路: 2的^n 轉化爲 2進制就是, 開頭1個1, 後邊全是0. 通過 n & (n - 1) 運算, 會去掉 最後1個1, 所以如果剩下是0, 則表示 這個數是 2的^n
     */
    public boolean isPowerOfTwo(int n) {
        //2的0次方最小是1
        return n > 0
                //2的次方都是只有1个1, 与 n- 1 即 比如011, 刚好是0.
                && (n & (n - 1)) == 0;
    }

    /**
     * 或者 用2進制, 比較 是不是 '開頭1個1, 後邊全是0' 也可以.
     */
    public boolean isPowerOfTwo2(int n) {
        if (n < 1) return false;
        while (n % 2 == 0)
            n /= 2;
        return n == 1;
    }
}
