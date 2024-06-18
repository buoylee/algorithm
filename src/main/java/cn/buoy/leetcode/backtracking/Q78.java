package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q78 {

    /**
     * https://www.youtube.com/watch?v=rtFHxiQAICA
     * 典型回溯超简单. 一直用下一个元素就好
     */
    public List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //第一个空, 已经先加入.
        dfs(S, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void dfs(int[] s, int index, List<Integer> path, List<List<Integer>> result) {
        //直接加入result
        result.add(new ArrayList<Integer>(path));

        for (int i = index; i < s.length; i++) {
            path.add(s[i]);
            dfs(s, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 78, 80 题
     * 思路: 也是回溯, 思路稍不同, 从左到右, 对每个元素做出'选'或'不选'.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
//        Arrays.sort(nums);  // non-descending order
        dfs(result, nums, new ArrayList<Integer>(), 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, int[] nums, List<Integer> tempList, int index) {
        if (index == nums.length) {
            result.add(new ArrayList<Integer>(tempList));
            return;
        }
        // not pick the number at this index, 不选, 直接下一个index, 不需要回溯.
        dfs(result, nums, tempList, index + 1);

        // pick the number at this index, 选择加入当前元素, 结尾回溯.
        tempList.add(nums[index]);
        dfs(result, nums, tempList, index + 1);
        tempList.remove(tempList.size() - 1);
    }

}
