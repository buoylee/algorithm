package cn.buoy.leetcode.math;

public class Q306 {
    /**
     * https://www.youtube.com/watch?v=LziJZT2uRwc
     */
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        // <= n / 2, 是因为 用substr会略掉 n / 2 这一位
        for (int i = 1; i <= n / 2; ++i)
            //因为1,2 位的和 的位数, 肯定大于等于 1,2位较大哪一数长度.
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j)
                if (isValid(i, j, num)) return true;
        return false;
    }

    private boolean isValid(int i, int j, String num) {
        //保证原str 取出的第1, 2 个数字 头不包含0即可.
        //后续数字 只需要判断是否等于 前2个数 的sum 即可.
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i + j));
        //start 是sum 数字 的第一位digit
        for (int start = i + j; start != num.length(); start += sum.length()) {
            //将上一轮sum 作为下一轮的 second
            x2 = x2 + x1;
            //将上一轮second 作为下一轮的 first
            x1 = x2 - x1;
            sum = x2.toString();
            //对比 是否以sum开头即可.
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
}
