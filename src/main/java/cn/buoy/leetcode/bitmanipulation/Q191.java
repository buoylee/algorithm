package cn.buoy.leetcode.bitmanipulation;

public class Q191 {
    /**
     * https://www.youtube.com/watch?v=0KAuHtVlN9Y
     * 簡單, 看視頻, 快速解.
     * 方法2: 用 n & (n - 1), 直到 n == 0.
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            //100100
            //100011
            //關鍵: n & (n - 1) 可以把從後邊數, 第一個1變成0.
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
