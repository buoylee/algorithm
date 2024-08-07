package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90 {
    /**
     * 簡單,視頻
     * https://www.youtube.com/watch?v=-tCHAdfDYDE
     * 思路: backtracking, 每加入一個 digit, tempList 都放入 result, 就是所有子集
     * 關鍵去重, 先排序, 然後相同層不能使用相同 digit
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 排序后, 在按index遍历时, 用来规避掉相同数字的重复组合.
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            // 跳过重复的字符 再加, 依赖前边的排序
            if (i > start && nums[i] == nums[i - 1]) continue;
            tempList.add(nums[i]);
            helper(result, tempList, nums, i + 1);
            // backtracking
            tempList.remove(tempList.size() - 1);
        }
    }
}
