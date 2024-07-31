package cn.buoy.leetcode.backtracking;

import java.util.*;

public class Q47 {
    /**
     * https://www.youtube.com/watch?v=X2aaIte3RK8
     * 思路:
     * 注意理解排除重复
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // 需要先排序, 为了处理相同数字重复组合问题. 解释在下边.
        Arrays.sort(nums);
        dfs(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void dfs(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 关键: nums[i] == nums[i - 1] && !used[i - 1] 用来限制在同一层(相同位置)不能再出现相同数字
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            tempList.add(nums[i]);
            dfs(list, tempList, nums, used);
            // backtracking
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q47 q47 = new Q47();
        List<List<Integer>> lists = q47.permuteUnique(new int[]{1, 1, 2});
    }
}
