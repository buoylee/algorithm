package cn.buoy.leetcode.math;

public class Q319 {
    /**
     * https://www.youtube.com/watch?v=6v7He5PszKs
     * 原理: 因为如果是完全平方数, 除了开根号的因数只有1个, 其他都是一对;
     * 题目可以转化为, 只要能1~n整除, 就可以切换开关;
     * 开灯的条件是, 开关次数为奇数, 否则为偶数, 要找的就是 能被 1~n 整除几次.
     * <p>
     * 例: 36 有多對 x * y == 36, 1 36, 2 18, 3 12, 4 9, 6 6, 總是能成對的出現,
     * 像 36 這樣的完全平方數(4, 9, 16), 都會有一個重複的數字對, 即只能在這個數字開關一次,
     * 又因爲 開關 偶數次 就是 關, 反之就是開.
     * 所以, 求的就是 1 到 n 有幾個完全平方數. 1^2, 2^2, 3^2, 4^2... 直接給n開平方, 向下取整就是答案.
     * <p>
     * 不然就暴力法!
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
