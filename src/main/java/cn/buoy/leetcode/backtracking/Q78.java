package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q78 {

    /**
     * https://www.youtube.com/watch?v=rtFHxiQAICA
     * 78, 80 题
     * 第一解思路是, 选或不选
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }
//        Arrays.sort(nums);  // non-descending order
        dfs(ans, nums, new ArrayList<Integer>(), 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] nums, List<Integer> list, int index) {
        if (index == nums.length) {
            ans.add(new ArrayList<Integer>(list));
            return;
        }
        dfs(ans, nums, list, index + 1);  // not pick the number at this index
        list.add(nums[index]);
        dfs(ans, nums, list, index + 1);  // pick the number at this index
        //不回溯, 会带上末尾的str
        list.remove(list.size() - 1);
    }

    /**
     * 思路是, 选择排列
     *
     * @param S
     * @return
     */
    public List<List<Integer>> subsets2(int[] S) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //第一个空, 已经先加入.
        dfs(S, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void dfs(int[] s, int index, List<Integer> path, List<List<Integer>> result) {
        //第一个空, 已经先加入.
        result.add(new ArrayList<Integer>(path));

        for (int i = index; i < s.length; i++) {
            path.add(s[i]);
            dfs(s, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }


}
