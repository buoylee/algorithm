package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90 {
    /**
     * https://www.youtube.com/watch?v=-tCHAdfDYDE
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //去重用
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> ls, int[] nums, int pos) {
        res.add(new ArrayList<>(ls));
        for (int i = pos; i < nums.length; i++) {
            //跳过重复的字符 再加, 依赖前边的排序
            if (i > pos && nums[i] == nums[i - 1]) continue;
            ls.add(nums[i]);
            helper(res, ls, nums, i + 1);
            ls.remove(ls.size() - 1);
        }
    }
}
