package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q46 {
    /**
     * 简单, 視頻
     * https://www.youtube.com/watch?v=9Pqv5ErFj_8
     * 思路: backtracking, 因为元素各不相同, 只要在组合时, 用 set 記錄 ele 是否已使用 即可.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary, 因爲 元素各不相同
        dfs(list, new ArrayList<>(), nums);
        return list;
    }

    private void dfs(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 关键: 每次add前, 都先检查 set 種是否有用过该数字.
            if (tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            dfs(list, tempList, nums);
            // backtracking
            tempList.remove(tempList.size() - 1);
        }
    }
}
