package cn.buoy.leetcode.array;

import java.util.HashMap;

public class Q325 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=RxIf_N6JiMM
     * 思路: 類似之前求 [i, j] 之间元素和, sum[j] - sum[i-1]
     * 有 2種 可能的 pre sum,
     * 1. 当前 [0 ~ i] 的 preSum;
     * 2. 当前 preSum 與 "之前 preSum" 的差为 result;
     * 從中選出 max 即可.
     * 注意: 如再次出现相同的dp, 跳过, 因为 計算 curr preSum - "之前的相同 preSum" 的距离肯定更大.
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int preSum = 0, result = 0;
        // 關鍵: map<preSum, index>
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            preSum = preSum + nums[i];
            // 關鍵: 表示从 index0 开始, 到 当前index 刚好 preSum == k 的情况, 直接賦值 result, 因爲是, 遍歷到當前 長度最大的時候.
            if (preSum == k)
                result = i + 1;
            else if (map.containsKey(preSum - k)) //查看之前是否有存過 preSum == x, 使得 "當前 preSum" - x, 刚好好是 k, 然后比較 max
                result = Math.max(result, i - map.get(preSum - k));
            // 關鍵: 如再次出现相同的 preSum, 跳过, 因为 計算 curr preSum - "之前的相同 preSum" 的距离肯定更大.
            if (!map.containsKey(preSum))
                map.put(preSum, i);
        }
        return result;
    }
}
