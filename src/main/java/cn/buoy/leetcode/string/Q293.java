package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q293 {
    /**
     * 弱智嗎, 視頻
     * https://www.youtube.com/watch?v=yMMQxFvlBro
     * 思路: 檢查 i - 1 和 i 是否都是 "+"
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            // 關鍵: 檢查 i - 1 和 i 是否都是 "+"
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                char[] arr = s.toCharArray();
                arr[i - 1] = '-';
                arr[i] = '-';
                res.add(new String(arr));
            }
        }
        return res;
    }
}
