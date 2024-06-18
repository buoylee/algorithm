package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q216 {
    /**
     * https://www.youtube.com/watch?v=qQcAm0CE21U
     */
    public List<List<Integer>> combinationSum3(int totalNum, int needingRemain) {
        List<List<Integer>> ans = new ArrayList<>();
        combination(ans, new ArrayList<Integer>(), totalNum, 1, needingRemain);
        return ans;
    }

    //needingRemain 表示 剩下需要加的数
    //start 表 从哪个数开始取
    private void combination(List<List<Integer>> ans, List<Integer> tempSubCombine, int totalNum, int start, int needingRemain) {
        if (tempSubCombine.size() > totalNum) {
            return;
        }
        if (tempSubCombine.size() == totalNum && needingRemain == 0) {
            List<Integer> li = new ArrayList<Integer>(tempSubCombine);
            ans.add(li);
            return;
        }
        for (int i = start; i <= needingRemain && i <= 9; i++) {
            tempSubCombine.add(i);
            combination(ans, tempSubCombine, totalNum, i + 1, needingRemain - i);
            tempSubCombine.remove(tempSubCombine.size() - 1);
        }
    }
}
