package cn.buoy.leetcode.array;

public class Q45 {
    /**
     * 不是很容易理解. 視頻.
     * https://www.youtube.com/watch?v=MknETKNOz_s
     * 思路: 贪心算法.
     * 本 step 最遠 index, 計算 "本 step 可到達的不同格子" 的 "最遠 index", 就可以 保持記錄, "本 step 最遠 index", 和 "next step 最遠 index",
     * 當到達 "本 step 最遠 index" 時, 說明至少完成了1 step, step++; 這時, 把 剛剛計算得到的 "next step 最遠 index" 作爲 下一輪的 "本 step 最遠 index", 如此循環直至 i 到底.
     */
    //fixme 贪婪, 选步数范围内最大值, 当到达最范围内最远处累加步数.
    public int jump(int[] nums) {
        int len = nums.length;
        int step = 0, currStepFarthestIdx = 0, nextStepFarthestIdx = 0;
        for (int i = 0; i < len - 1; i++) {
            // 給每個格子, 計算可以走到的最遠的 index. 爲了得到 next step farthest index.
            nextStepFarthestIdx = Math.max(nextStepFarthestIdx, i + nums[i]);
            // 關鍵: 當 i 遍歷到 "本 step 最遠格子" 時, 也就完成了 1個 step, 步數++; 且 把 "next step 最遠 index" 賦值給 "當前 step 最遠可到達 index".
            if (i == currStepFarthestIdx) {
                step++;
                currStepFarthestIdx = nextStepFarthestIdx;
            }
        }
        return step;
    }

    /**
     * dp 超時, 但是思路可以學習.
     */
    public int jump2(int[] nums) {
        if (nums.length == 1) return 0;
        int[] dp = new int[nums.length];
        // init
        for (int i = 1; i < nums.length; i++)
            dp[i] = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 給 "i + 1" 到 "i 能最遠 index" 的所有dp[]取最小可能的 step, min(dp[], dp[i] + 1)
            for (int j = i + 1; j <= i + nums[i] && j < nums.length; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[nums.length - 1];
    }

}
