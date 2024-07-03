package cn.buoy.leetcode.math;

public class Q400 {
    /**
     * 還是找規律, 視頻
     * https://www.bilibili.com/video/BV13i4y1P7pX?from=search&seid=11321764971238763980
     * 思路: 通過 不同位數, 定位 到底是目標是在哪個數上.
     */
    public int findNthDigit(int n) {
        //len 数有幾位
        //range 对应位数的 数字个数
        //i 表示找到的 第几个数字.
        //n 是 离n 还有几个digit
        int len = 1, i = 1;
        long range = 9;
        while (n > len * range) {
            n -= len * range;
            len++;
            range *= 10;
            i *= 10;
        }
        //因为 i从 1, 10, 100 开始, 所以 n-1, 除len 得出 还差几个数字,
        i += (n - 1) / len;
        String s = Integer.toString(i);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }
}
