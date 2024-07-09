package cn.buoy.leetcode.string;

import java.util.LinkedList;
import java.util.List;

public class Q241 {
    /**
     * https://www.youtube.com/watch?v=3r8DVBDkLWg
     * 思路: 分治, 從 不同的運算符號 切分成 左右2邊, 然後dfs下去, 直到只有數字.
     */
    public List<Integer> diffWaysToCompute(String input) {
        // 這是 遞歸內的 result, 只會 記錄 範圍內的 result.
        List<Integer> result = new LinkedList<Integer>();
        // 被分隔成沒有運算符的str; 和下邊的作用一樣
//        if (input.matches("\\d+")) {
//            result.add(Integer.valueOf(input));
//            return result;
//        }
        //找 任意運算符 作爲切分點.
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (currChar == '-' || currChar == '*' || currChar == '+') {
                // 運算符的左右2邊繼續 dfs
                List<Integer> part1Res = diffWaysToCompute(input.substring(0, i));
                List<Integer> part2Res = diffWaysToCompute(input.substring(i + 1));
                for (Integer p1 : part1Res) {
                    for (Integer p2 : part2Res) {
                        if (currChar == '+')
                            result.add(p1 + p2);
                        else if (currChar == '-')
                            result.add(p1 - p2);
                        else if (currChar == '*')
                            result.add(p1 * p2);
                    }
                }

            }
        }
        // 被分隔成沒有運算符的str
        if (result.size() == 0)
            result.add(Integer.valueOf(input));
        return result;
    }
}
