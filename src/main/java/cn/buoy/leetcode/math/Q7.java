package cn.buoy.leetcode.math;

public class Q7 {
    /**
     * 简单
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;

        //知道原数字为0
        while (x != 0) {
            //取末尾
            int tail = x % 10;
            //之前的数字都左移一位 + 刚取得 末尾
            int newResult = result * 10 + tail;
            //还原, 看是否能还原到上一步, 不等, 说明越界
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            //去掉原数字末位
            x = x / 10;
        }

        return result;
    }
}
