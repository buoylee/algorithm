package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q78 {
    /**
     * 77
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=rtFHxiQAICA
     * https://www.youtube.com/watch?v=-0OycisNZTo 短
     * 思路: backtracking, 典型排列組合, n選1, n-1選1, ...
     * temp 每加入一個元素到 temp, 就放入 result, 就能得到所有子集.
     * 題目限制選 num 只能由小到大, 直接 start + 1 然後遍歷 即可.
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void dfs(int[] s, int start, List<Integer> temp, List<List<Integer>> result) {
        // 第一个是 ""
        // 每一步都加入 result
        result.add(new ArrayList<Integer>(temp));
        for (int i = start; i < s.length; i++) {
            temp.add(s[i]);
            // 直接 i + 1 給 dfs 即可.
            dfs(s, i + 1, temp, result);
            // backtracking
            temp.remove(temp.size() - 1);
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
