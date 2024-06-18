package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q39 {
    /**
     * https://www.youtube.com/watch?v=6BmmaS3n-Q8
     * 回过头来看简单.
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        //不排序也能通过, 因为原arr已经保证元素各不相同, 且遍历时只能往后.
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0)
            return;
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            //关键在此, 这里的start可以传的和上层相同start, 因为可以重复使用, 但一旦跳过此数后, 只能用往后其他数字, 不会造成重复的组合方式(题目保证arr中元素各不相同).
            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }
    }
}
