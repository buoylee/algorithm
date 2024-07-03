package cn.buoy.leetcode.math;

public class Q292 {
    /**
     * https://www.youtube.com/watch?v=JoYV1uJaOu4
     * 思路: 當你後手時, 確保當你抽完, 剩下的n 可以 %4 == 0, 贏
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
