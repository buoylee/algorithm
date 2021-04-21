package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q301 {
    /**
     * https://www.youtube.com/watch?v=lEMDEmQje1Q
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
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
}
