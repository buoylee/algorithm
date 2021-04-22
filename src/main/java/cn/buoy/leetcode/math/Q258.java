package cn.buoy.leetcode.math;

public class Q258 {
    /**
     * https://www.youtube.com/watch?v=7ab7S8wvy0s
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        int digitalRoot = 0;
        while (num > 0) {
            digitalRoot += num % 10;
            num = num / 10;

            if (num == 0 && digitalRoot > 9) {
                num = digitalRoot;
                digitalRoot = 0;
            }
        }
        return digitalRoot;
    }
}
