package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q131 {
    /**
     * 简单, 視頻, 代碼
     * https://www.youtube.com/watch?v=dl1gtKWXKEs
     * 思路: 因爲是要具體的 substr list, 回溯,
     * 遍歷 所有切點, 每次取 'head 到 切點'(前段) 的 substr 判斷是否爲迴文,
     * 然後 '切點 到 tail'(後段) 繼續 放入 dfs, 直到 start == s.length()
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> tempList = new ArrayList<String>();
        dfs(s, 0, tempList, res);
        return res;
    }

    public void dfs(String s, int start, List<String> list, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<String>(list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            //每次都 判断前部分是否回文
            if (isPal(s, start, i)) {
                list.add(s.substring(start, i + 1));
                dfs(s, i + 1, list, res);
                //backtracking
                list.remove(list.size() - 1);
            }
        }
    }

    // 是否迴文
    public boolean isPal(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        return true;
    }
}
