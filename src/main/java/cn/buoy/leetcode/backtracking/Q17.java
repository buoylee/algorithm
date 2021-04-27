package cn.buoy.leetcode.backtracking;

import java.util.LinkedList;
import java.util.List;

public class Q17 {
    /**
     * https://www.youtube.com/watch?v=1moKZtCU2QI
     * BFS
     */
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        //注意看digit len, mapping 只是映射数字键而已!
        //直到 传入的 digit len 到达最后, return
        for (int i = 0; i < digits.length(); i++) {
            //从头拆除digit
            int x = Character.getNumericValue(digits.charAt(i));
            //从 ans 元素len == 0 开始, 只要 ans 元素len == digits index, 就会append digit.
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    //append完 推入,
                    ans.add(t + s);
            }
        }
        return ans;
    }


    //backtracking, DFS, 时空优
    public List<String> letterCombinations2(String digits) {
        List<String> list = new LinkedList<>();
        if (digits == null || digits.length() == 0) return list;
        char[][] map = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        backtrack(digits, list, map, new StringBuilder(), 0);
        return list;
    }

    /**
     * @param digits 入口传入的数字
     * @param list   结果
     * @param map    映射的字母arr
     * @param sb     temp str
     * @param start  当前添加 第 start 位(0~digits.len) 数字
     */
    private void backtrack(String digits, List<String> list, char[][] map, StringBuilder sb, int start) {
        //到达 传入的数字 digits 长度, return.
        if (start == digits.length()) {
            list.add(new String(sb));
            return;
        }
        //取传入数字 第i位 digit,
        int num = digits.charAt(start) - '0';
        for (int i = 0; i < map[num].length; i++) {
            sb.append(map[num][i]);
            //刚 拼接好的str 和 下一位要加入的index 传入.
            backtrack(digits, list, map, sb, start + 1);
            //backtracking 一下
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
