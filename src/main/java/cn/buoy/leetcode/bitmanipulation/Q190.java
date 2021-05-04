package cn.buoy.leetcode.bitmanipulation;

public class Q190 {
    /**
     * https://www.youtube.com/watch?v=OJE5k71dH1U
     */
    public int reverseBits(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            //res 左移 给当前位准备位置.
            result <<= 1;
            //原数取末尾, 是1 则res++(等于当前位置1)
            if ((n & 1) == 1) result++;
            //原数右移
            n >>= 1;
        }
        return result;
    }
}
