package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90 {
    /**
     * https://www.youtube.com/watch?v=-tCHAdfDYDE
     * 和之前的题都一样, 注意点在:
     * if (i > pos && nums[i] == nums[i - 1]) continue;
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //排序后, 在按index遍历时, 用来规避掉相同数字的重复组合.
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public void helper(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            //跳过重复的字符 再加, 依赖前边的排序
            if (i > start && nums[i] == nums[i - 1]) continue;
            tempList.add(nums[i]);
            helper(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
