package cn.buoy.leetcode.math;

public class Q8 {
    /**
     * https://www.youtube.com/watch?v=FcT5-yrKtyY
     * 第2中方式, 之前也遇过, 就是判断越界,
     * 算出数后, 在除 10 对比 上次的数 是否相同, 不同则越界.
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        //符号, 上次组合到的数字, str的index,
        int sign = 1, base = 0, i = 0, INT_MAX = Integer.MAX_VALUE, INT_MIN = Integer.MIN_VALUE;
        //去空格
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i >= str.length()) return 0;
        //检查符号
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            if (str.charAt(i) == '-') sign = -1;
            i++;
        }

        //遍历判断是否为数字
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            //判断是否int越界, 比较 max的base(除个位) 和 个位. min, max 最后一位 是 8, 7 大于, 这里就可以覆盖全部. 或者用 INT_MAX % 10 > str.charAt(i) - '0'
            if (base > INT_MAX / 10 || (base == INT_MAX / 10 && str.charAt(i) - '0' > 7)) {
                if (sign == -1) return INT_MIN;
                else return INT_MAX;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }

        return base * sign;

    }
}
