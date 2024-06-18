package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q40 {
    /**
     * https://www.youtube.com/watch?v=2Olq077uuP8
     * [1, 2, 2, 2, 2], 5
     * 1,
     * 1, 2
     * 1, 2, 2
     * 1, 2, 2(同一层, i++, 错)
     * 再次强调!!!, 解题时, 想不出来的! 一步步写下来看!
     * <p>
     * [1, 2(1), 2(2), 2(3), 5], 10
     * 1
     * 1 2
     * 1 2(1) 2(2)
     * 1 2(1) 2(2) 2(3) X
     * 1 2(1) 2(2) 2(3) 5 X
     * 1 2(1) 2(2) 5
     * 1 2(1) 2(3) 5 X
     * 1 2(1) 5 X
     * 1 2(2) 2(3) 5 X
     * 1 2(2) 5 X
     * 1 2(3) 5 X
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        //为了跳过重复元素, 做的排序
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            //可以从任意index开始, 但是不能往回找元素.
            for (int i = start; i < nums.length; i++) {
                //重要
                //这里只会跳某一轮的重复元素, 如果arr中本来就有重复元素, 是可以重复是用的.
                //视频里解释了, 将不同元素相同数字的重复组合交给下层处理
                if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
