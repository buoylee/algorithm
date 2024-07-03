package cn.buoy.leetcode.math;

public class Q69 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=JrBlp8xWqSg
     * 思路: 2分, 通過 mid^2 和 x 比大小來定位 結果.
     */
    public int mySqrt(int x) {
        long start = 1;
        long end = x;
        // 2分好用寫法.
        while (start + 1 < end) {
            // 防越界
            long mid = start + (end - start) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // 右邊的end^2都比x小了, 左邊的start只會更小.
        if (end * end <= x) {
            return (int) end;
        } else {
            return (int) start;
        }
    }
}
