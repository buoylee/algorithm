package cn.buoy.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class Q386 {
    /**
     * 簡單, 視頻
     * https://leetcode.com/problems/lexicographical-numbers/discuss/86231/Simple-Java-DFS-Solution
     * https://www.youtube.com/watch?v=zDQDVtyBv1A 看這個
     * 思路: dfs, 每一位表示一層,
     */
    public List<Integer> lexicalOrder(int max) {
        List<Integer> res = new ArrayList<>();
        // 數字首位不可能爲0, 所以只有 1~9
        for (int i = 1; i < 10; ++i)
            dfs(i, max, res);
        return res;
    }

    public void dfs(int cur, int max, List<Integer> res) {
        if (cur > max)
            return;
        // 位數少的排在前
        res.add(cur);
        // 本層(同一位) 0~9
        for (int i = 0; i < 10; ++i) {
            if (10 * cur + i > max)
                return;
            // dfs 先進入下一位
            dfs(10 * cur + i, max, res);
        }
    }
}
