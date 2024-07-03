package cn.buoy.leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=v5XssGxx60U
     * 思路: 把 每个元素都用 target - current value, 得到 remain, 如果 map 中存在 remain, 则得到答案, 然后把 遍历过的元素 放入 map(value, index),
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueIndexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            // 只要 map 中存在 diff, 返回结果.
            int diff = target - nums[i];
            if (valueIndexMap.containsKey(diff))
                return new int[]{valueIndexMap.get(diff), i};
            // 放入map, 为了 和后边元素 组成 target.
            valueIndexMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
