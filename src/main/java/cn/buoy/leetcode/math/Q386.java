package cn.buoy.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class Q386 {
    /**
     * https://leetcode.com/problems/lexicographical-numbers/discuss/86231/Simple-Java-DFS-Solution
     * 当做一颗 10叉树来看, 但是根节点root 只有 1~9. 用DFS遍历.
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, res);
        }
        return res;
    }

    public void dfs(int cur, int n, List<Integer> res) {
        if (cur > n)
            return;
        else {
            res.add(cur);
            for (int i = 0; i < 10; ++i) {
                if (10 * cur + i > n)
                    return;
                dfs(10 * cur + i, n, res);
            }
        }
    }
}
