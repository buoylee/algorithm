package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q216 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=qQcAm0CE21U
     * 思路: backtracking, 典型排列组合, n选1, n-1选1, ... 直到 curr digit num == digitNum, 然后 判断是否刚好 remain == 0 即可.
     */
    public List<List<Integer>> combinationSum3(int totalNum, int remain) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<Integer>(), totalNum, 1, remain);
        return ans;
    }

    // remain 表示 剩下需要加的数
    // digitNum 表示 总共可以取几个 digit
    private void dfs(List<List<Integer>> ans, List<Integer> temp, int digitNum, int start, int remain) {
        if (temp.size() > digitNum)
            return;
        if (temp.size() == digitNum && remain == 0) {
            List<Integer> li = new ArrayList<Integer>(temp);
            ans.add(li);
            return;
        }
        for (int i = start; i <= remain && i <= 9; i++) {
            temp.add(i);
            dfs(ans, temp, digitNum, i + 1, remain - i);
            // backtracking
            temp.remove(temp.size() - 1);
        }
    }
}
