package cn.buoy.leetcode.bitmanipulation;

public class Q191 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=0KAuHtVlN9Y
     * 思路: 关键知道用 n & (n - 1), 就能消除 n 的 最后一个 '1' 的 bit. 如此循环到 n == 0 即可退出.
     * 这样就不用每一位都进行判断是不是1.
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            // 100100
            // 100011
            // 關鍵: n & (n - 1), 可以把最后一个 '1' 變成 '0'.
            n &= (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 方法1: 用 n & 1, 然後 n >> 1, 直到結束.
     */
    public static int hammingWeight2(int n) {
        int ones = 0;
        while (n != 0) {
            ones = ones + (n & 1);
            n = n >>> 1;
        }
        return ones;
    }


}
