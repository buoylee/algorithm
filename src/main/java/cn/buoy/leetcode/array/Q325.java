package cn.buoy.leetcode.array;

import java.util.HashMap;

public class Q325 {
    /**
     * https://www.youtube.com/watch?v=RxIf_N6JiMM
     * 像之前的求[i, j] 之间元素和, sum[j] - sum[i-1]
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;
                //相当于 找 sum[j] - sum[i-1]  的 sum[i-1], 即找到sum[i, j], 最后选出max即可.
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            //前缀和, map<sum, index>
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }
}
