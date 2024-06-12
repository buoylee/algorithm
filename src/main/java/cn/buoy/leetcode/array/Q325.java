package cn.buoy.leetcode.array;

import java.util.HashMap;

public class Q325 {
    /**
     * https://www.youtube.com/watch?v=RxIf_N6JiMM
     * 像之前的求[i, j] 之间元素和, sum[j] - sum[i-1]
     * 思路: dp[i]: 包括i和之前的sum, 1. 当前dp于之前dp之间的差为result; 2. 当前value; 如再次出现相同的dp, 跳过, 因为之前的相同dp的距离肯定更大.
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        //key为dp(sum), value为index
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            //表示从index==0开始, 到当前index刚好sum==k的情况.
            if (sum == k)
                max = i + 1;
                //查看是否存在x, 使得 sum-x, 刚好好是k, 然后选择记录更大的width
            else if (map.containsKey(sum - k))
                max = Math.max(max, i - map.get(sum - k));
            //前缀和, map<sum, index>
            //如再次出现相同的dp, 跳过, 因为之前的相同dp的距离肯定更大.
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        return max;
    }
}
