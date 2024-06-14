package cn.buoy.leetcode.backtracking;

import java.util.*;

public class Q47 {
    public static void main(String[] args) {
        Q47 q47 = new Q47();
        List<List<Integer>> lists = q47.permuteUnique(new int[]{1, 1, 2});
    }

    /**
     * https://www.youtube.com/watch?v=X2aaIte3RK8
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        //需要先排序, 为了处理相同数字重复组合问题. 解释在下边.
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                //~~在挑选某位digit的时候, 如果(当前元素与前一个元素相同), 如果(前一个元素使用 后一个不使用), 会与 (前一个元素没使用,而后一个元素使用了)重复, 所以要排除一种, 这里选择排除后者.~~
                //关键:
                //used[i]用来限制在同一层(相同位置)不能出现再出现相同数字
                //i > 0 && nums[i] == nums[i - 1] && !used[i - 1], 因为nums排过序, 这样使得相同的数字使用时, 必须从左到右一个个使用, 使得相同数字排列顺序唯一(不会先使用后边再用前边, 造成相同的nums组合出现).
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
