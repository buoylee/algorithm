package cn.buoy.leetcode.bitmanipulation;

public class Q190 {
    /**
     * 簡單, 視頻.
     * https://www.youtube.com/watch?v=OJE5k71dH1U
     * 思路: 從 "最後一 bit" 開始檢查 "原 num",
     * result << 1(提前左移, 方便本次 +1),
     * (原 num & 1) 獲取最後一位 是1或0,
     * 如果是1, 則 result + 1,
     * 原 num >> 1(檢查倒數下一位),
     * 直到32位檢查結束.
     */
    public int reverseBits(int n) {
        if (n == 0) return 0;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // result 左移 给当前位准备位置.
            // 细节: 如果 result <<= 1 写在 +1 后边, 会使 result 最终多左移了一位. 但是对于第一次左移时, result 是 0, 并不会影响结果
            result <<= 1;
            // 原数取末尾 bit, 是1, 则 res++(等于当前位置1)
            if ((n & 1) == 1) result++;
            // 原数右移
            n >>= 1;
        }
        return result;
    }
}
