package cn.buoy.leetcode.math;

public class Q319 {
    /**
     * https://www.youtube.com/watch?v=8sF5HIJxlXE
     * 原理: 因为如果是完全平方数, 除了开根号的因数只有1个, 其他都是一对;
     * 题目可以转化为, 只要能1~n整除, 就可以切换开关;
     * 开灯的条件是, 开关次数为奇数, 否则为偶数, 要找的就是 能被 1~n 整除几次.
     * <p>
     * 不然就暴力法!
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
