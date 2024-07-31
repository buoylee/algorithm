package cn.buoy.leetcode.backtracking;

import java.util.LinkedList;
import java.util.List;

public class Q17 {
    /**
     * 簡單, 視頻, 或直接註釋
     * https://www.youtube.com/watch?v=1moKZtCU2QI
     * 思路: 典型 bfs, 從 "第一個 digit" 對應的 letters 開始 bfs 擴展, 直接 "最後一個 digit".
     */
    public List<String> letterCombinations(String digits) {
        LinkedList<String> result = new LinkedList<String>();
        if (digits.isEmpty()) return result;
        String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}; // 實際數字 與 字母 映射
        // bfs, "" 作爲起始 node, 因爲 result 是 str, "" 不會影響結果.
        // "" -> "a", "b", "c";
        // "a", "b", "c" -> a + d, a + e, a + f...
        result.add("");
        // 從頭遍歷 傳入的 digits arr 每個 digit
        for (int digitIdx = 0; digitIdx < digits.length(); digitIdx++) {
            int digit = Character.getNumericValue(digits.charAt(digitIdx));
            // 關鍵: 如果 link 裏的 第一個 ele 的 "字符 len" 還處於 當前層(0, 1, 2...), 還有沒處理的 node, 繼續 append 該 digit 對應的 letter arr.
            String topStr = result.peek();
            while (topStr.length() == digitIdx) {
                // 彈出 需要處理(append)的 tempStr
                String tempStr = result.remove();
                // 逐一 append 對應 digit 的 letterArr ele
                for (char letter : mapping[digit].toCharArray())
                    //例: a + d; a + e; a + f;
                    result.add(tempStr + letter);
            }
        }
        return result;
    }


    /**
     * 典型 backtracking, 都簡單
     */
    public List<String> letterCombinations2(String digits) {
        List<String> result = new LinkedList<>();
        if (digits == null || digits.length() == 0) return result;
        char[][] digitToLettersMap = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        backtrack(digits, result, digitToLettersMap, new StringBuilder(), 0);
        return result;
    }

    /**
     * @param digits 入口传入的数字
     * @param list   结果(一个完整的sb从digit[0]~digit[len-1]的append完后, 放入list)
     * @param map    映射的字母arr
     * @param temp   temp str(一个组合的temp str, 例: adg)
     * @param start  当前添加 第 start 位(0~digits.len) 数字
     */
    private void backtrack(String digits, List<String> list, char[][] map, StringBuilder temp, int start) {
        //到达 传入的数字 digits 长度, return.
        if (start == digits.length()) {
            list.add(new String(temp));
            return;
        }
        //取传入数字 第i位 digit,
        int num = digits.charAt(start) - '0';
        for (int i = 0; i < map[num].length; i++) {
            //例: a
            temp.append(map[num][i]);
            //刚 拼接好的str 和 下一位要加入的index 传入.
            // 例: ad
            backtrack(digits, list, map, temp, start + 1);
            //backtracking 一下
            //例" a
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
