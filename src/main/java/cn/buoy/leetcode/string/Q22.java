package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q22 {
    /**
     * https://www.youtube.com/watch?v=ptYZDPk2bOg
     * 简单
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", n, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close) {
        //都用完则退出
        if (open == 0 && close == 0) {
            list.add(str);
            return;
        }

        //只要 左没用完 都没用
        if (open > 0)
            backtrack(list, str + "(", open - 1, close);
        //只要 左小于右 都可以用
        if (open < close)
            backtrack(list, str + ")", open, close - 1);
    }
}
