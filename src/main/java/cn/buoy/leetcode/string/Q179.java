package cn.buoy.leetcode.string;

import java.util.Arrays;
import java.util.Comparator;

public class Q179 {
    /**
     * 簡單
     * https://leetcode.com/problems/largest-number/submissions/
     * https://www.youtube.com/watch?v=LUxREjEADCw 短
     * 思路: 排序成 高位較大的數 在前.
     */
    public String largestNumber(int[] num) {
        if (num == null || num.length == 0)
            return "";
        String[] numStrs = new String[num.length];
        for (int i = 0; i < num.length; i++)
            numStrs[i] = String.valueOf(num[i]);
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                // 关键: 很巧妙的比較出, 高位較大的數
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1);
            }
        };
        Arrays.sort(numStrs, comp);
        // An extreme edge case by lc, say you have only a bunch of 0 in your int array
        // 如果 第一個就是0, 不是 合法數字, 直接返回0
        if (numStrs[0].charAt(0) == '0')
            return "0";
        StringBuilder sb = new StringBuilder();
        for (String s : numStrs)
            sb.append(s);
        return sb.toString();
    }
}
