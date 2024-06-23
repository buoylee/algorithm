package cn.buoy.leetcode.bitmanipulation;

public class Q190 {
    /**
     * https://www.youtube.com/watch?v=OJE5k71dH1U
     * 簡單. 看視頻.
     * 思路: 從最後一位開始檢查原num, (原num & 1) 獲取最後一位 是1或0, 如果是1則需要result先++1, 然後 result << 1(讓位給下一位), 原num >> 1(檢查倒數下一位num的值), 直到32位檢查結束.
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
