package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q39 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=6BmmaS3n-Q8
     * 思路: backtracking, 在 ele 各不相同的前提下, 即使可以重複使用 ele, 也可以保證不會有重複組合, 因爲不能回頭取已經經過的 ele,
     * dfs 剩餘的數 = remain - curr, 到 remain < 0 或 == 0 就返回, remain == 0, tempList 放入 result
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        // 關鍵: 不排序也能通过, 因为原arr已经保证元素各不相同, 且遍历时只能往后.
        dfs(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void dfs(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0)
            return;
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            //关键: 这里的 start 可以传的和上层相同 start(num 重复使用, 但不能使用已經經過的 ele), 這樣就不会造成 重复的组合方式(题目保证arr中元素各不相同).
            dfs(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            // backtracking
            tempList.remove(tempList.size() - 1);
        }
    }
}
