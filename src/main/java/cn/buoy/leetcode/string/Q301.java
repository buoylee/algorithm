package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q301 {
    /**
     * 這個還沒看, 時間比較優. 有空再回來看. 可以先看下邊的.
     * https://www.youtube.com/watch?v=lEMDEmQje1Q
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        //The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
        //所以, 其实这里不需要stack, 只要不小于0.
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;

            //从没检查过的最后删除的位置开始(第一次从index 0 开始)到尾(当前遍历到的index)删 不合法的")", 只删 第一个 或 连续的第一个")"
            for (int j = last_j; j <= i; ++j)
                //j == last_j 是避免一开头就是")"的情况
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        //反转整个str, 用来删除无效的"(".
        String reversed = new StringBuilder(s).reverse().toString();
        //这里保证了 添加到结果中的str 肯定是恢复顺序的.
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    /**
     * backtracking 比較好理解, 時間複雜度較高, 視頻停下思路就好, 代碼.
     * https://www.youtube.com/watch?v=I91977216Hk
     * 思路: 通過遍歷 刪除/不刪除 currChar, 不斷 dfs, 直到 leftRemove == 0 && rightRemove == 0 && open == 0
     * dfs 期間, 依賴 "還需要刪除的 leftRemove/rightRemove" 和 "open"(有多少 '(' 沒有閉合) 來解決 括號的合法性.
     */
    public List<String> removeInvalidParentheses2(String s) {
        Set<String> result = new HashSet<>();
        // 表示需要移除的 '(' 和 ')' 的個數.
        int[] count = getMinRemoveCounts(s);
        backtrack(s, 0, count[0], count[1], new StringBuilder(), 0, result);
        return new ArrayList<>(result);
    }

    /**
     * @param s
     * @param index
     * @param leftRemove
     * @param rightRemove
     * @param temp
     * @param open        用于跟踪当前字符串中未匹配的左括号, 這個解法不用 完整檢查 str 是否 合法, 因爲這裏檢查了所有 '(' 是否完全閉合.
     * @param result
     */
    private void backtrack(String s, int index, int leftRemove, int rightRemove, StringBuilder temp, int open, Set<String> result) {
        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0 && open == 0)
                result.add(temp.toString());
            return;
        }
        char currChar = s.charAt(index);
        int len = temp.length();
        // 這裏是 進行 刪除 currChar 的 dfs 操作.
        // 因爲只有可能 leftRemove 或 rightRemove 其一 > 0, 所以只會有一個執行.
        if (currChar == '(' && leftRemove > 0)
            backtrack(s, index + 1, leftRemove - 1, rightRemove, temp, open, result);
        if (currChar == ')' && rightRemove > 0)
            backtrack(s, index + 1, leftRemove, rightRemove - 1, temp, open, result);

        // 這裏才是 添加 currChar 的 backtracking 的開始
        temp.append(currChar);
        if (currChar != '(' && currChar != ')') {
            backtrack(s, index + 1, leftRemove, rightRemove, temp, open, result);
        } else if (currChar == '(') { // 爲什麼 這裏加入 currChar, 不用 leftRemove ++, 因爲 leftRemove是針對整個 str 的, 這個 append currChar, 只不過是 原本的數量之一.
            backtrack(s, index + 1, leftRemove, rightRemove, temp, open + 1, result);
        } else if (currChar == ')' && open > 0) {
            backtrack(s, index + 1, leftRemove, rightRemove, temp, open - 1, result);
        }
        // backtracking
        temp.setLength(len);
    }

    /**
     * 表示需要移除的 '(' 和 ')' 的個數, 只有可能 其一('(' 或 ')') > 1, 或 都等於0.
     */
    private int[] getMinRemoveCounts(String s) {
        int leftRemove = 0, rightRemove = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                leftRemove++;
            else if (c == ')')
                if (leftRemove > 0)
                    leftRemove--;
                else
                    rightRemove++;
        }
        return new int[]{leftRemove, rightRemove};
    }
}
