package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q46 {
    /**
     * https://www.youtube.com/watch?v=9Pqv5ErFj_8
     * <p>
     * 简单, 因为元素各不相同, 只要在组合时, 判断是否是使用过元素即可.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                //关键: 每次add前, 都先检查是否有用过该数字.
                if (tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
