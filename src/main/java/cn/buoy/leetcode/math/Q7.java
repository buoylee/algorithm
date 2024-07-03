package cn.buoy.leetcode.math;

public class Q7 {
    /**
     * 简单, 視頻, 這個寫法比較直觀, 2解不需要用long(用是否能還原上一步result來判斷).
     * https://www.youtube.com/watch?v=n8Ko9BWALc8
     * 思路:不斷把原數的 末尾數字 作爲新數的 末尾數字, 細節在於 判斷 新數可能越界. 例: 1....7 => 7....1(超過int範圍)
     */
    public int reverse(int x) {
        long result = 0;
        while (x != 0) {
            // 得到 原數 最後一位數字, 並加入到 result 最後一位.
            result = result * 10 + x % 10;
            // 原數 右移
            x = x / 10;
            // 越界
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
                return 0;
        }
        return (int) result;
    }

    /**
     * https://www.youtube.com/watch?v=j8z-OK1dI3I
     * 差別在於規避 越界 處理.
     */
    public int reverse2(int x) {
        int result = 0;
        while (x != 0) {
            int lastDigit = x % 10;
            int newResult = result * 10 + lastDigit;
            // 關鍵: 把 newResult 还原成 result, 如果不等, 说明越界.
            if ((newResult - lastDigit) / 10 != result)
                return 0;
            result = newResult;
            x = x / 10;
        }
        return result;
    }
}
