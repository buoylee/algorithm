package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q22 {
    /**
     * 简单, 視頻, 代碼, 沒有 backtracking, 只是 dfs.
     * https://www.youtube.com/watch?v=ptYZDPk2bOg
     * 思路: 只有 剩餘的左括號 少於 右括號, 才可以使用 右括號.
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        dfs(result, "", n, n);
        return result;
    }

    // 關鍵: left 表示 剩餘的左括號
    // 關鍵: right 表示 剩餘的右括號
    public void dfs(List<String> list, String temp, int left, int right) {
        //都用完则退出
        if (left == 0 && right == 0) {
            list.add(temp);
            return;
        }
        if (left > 0)
            dfs(list, temp + "(", left - 1, right);
        //只要 剩餘的左括號 少於 右括號, 就可以使用 右括號
        if (left < right)
            dfs(list, temp + ")", left, right - 1);
    }
}
