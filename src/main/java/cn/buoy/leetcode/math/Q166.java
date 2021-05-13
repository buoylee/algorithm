package cn.buoy.leetcode.math;

import java.util.HashMap;
import java.util.HashSet;

public class Q166 {
    /**
     * https://www.youtube.com/watch?v=B3kA9deZQoA&t=176s
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // integral part
        //余数为0, 则直接返回.
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // fractional part
        res.append(".");
        //存放出现过的`小数数字`, 开始重复, 则break
        //()是要出现在重复的部分, 并不是所有的小数部分都会重复!, 所以要用map来存.
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        HashSet<Long> set = new HashSet<>();
        //存为
        map.put(num, res.length());
        set.add(num);
        //取余*10 再除, 直到为0或出现重复数字
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            //重复出现在哪个数字, `(` 就从到`他的index`插入.
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}
