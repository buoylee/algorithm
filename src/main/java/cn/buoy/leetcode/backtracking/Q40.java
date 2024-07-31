package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q40 {
    /**
     * 簡單, 視頻, 註釋.
     * https://www.youtube.com/watch?v=2Olq077uuP8
     * 思路: backtracking, 不斷用 remain - curr 來 dfs,
     * remain < 0 直接返回;
     * remain == 0 加入 result.
     * 關鍵: 去除重複組合,
     * 1. arr 排序, 避免 "相同 num" 組成 "不同的排列" 的組合;
     * 2. result 的同一 index, 不能使用相同 num, 避免用 "不同 index 但 相同的 num" 組成組合.
     * <p>
     * 例子:
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
        // 關鍵: 为了跳过重复元素, 做的排序
        Arrays.sort(nums);
        dfs(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void dfs(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 關鍵: 这里会跳過 同一層的 "同樣 num", 如果 arr 中本来就有重复元素, 是沒問題的.
            // i > start(從第2個 ele 開始), nums[i] == nums[i - 1](和前一個 ele 相同), 就跳過.
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            tempList.add(nums[i]);
            dfs(list, tempList, nums, remain - nums[i], i + 1); // start == i + 1. 意思是, 不能重複使用 同一個 index 的 ele
            // backtracking
            tempList.remove(tempList.size() - 1);
        }
    }
}
