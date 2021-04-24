package cn.buoy.leetcode.math;

public class Q292 {
    /**
     * https://www.youtube.com/watch?v=JoYV1uJaOu4
     * 只要能筹够4块石头的倍数 给对手 都不能赢.
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
