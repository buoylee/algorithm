package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q131 {
    /**
     * https://www.youtube.com/watch?v=dl1gtKWXKEs
     * 简单!
     * 思路: 每次切分别, 都要判断`左边到上次切点`内 是否是回文
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        dfs(s, 0, list, res);
        return res;
    }

    public void dfs(String s, int pos, List<String> list, List<List<String>> res) {
        if (pos == s.length()) {
            res.add(new ArrayList<String>(list));
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            //每次都 判断前部分是否回文
            if (isPal(s, pos, i)) {
                list.add(s.substring(pos, i + 1));
                dfs(s, i + 1, list, res);
                //backtracking
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPal(String s, int low, int high) {
        while (low < high) if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }
}
