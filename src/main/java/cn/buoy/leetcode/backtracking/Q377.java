package cn.buoy.leetcode.backtracking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q377 {
    /**
     * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     * leetcode 解释 dp[0]=1, 果然有点奇怪.(應該可以忽略, 不管就好)
     * EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way. Since target == 0 only happens when in the previous call, target = nums[i], we know that this is the only combination in this case, so we return 1.
     * <p>
     * https://www.youtube.com/watch?v=JFB0HZ5DcSw
     * 注意: 關鍵, 因爲只要滿足target, 任意元素的排列組合都是合法的. 所以下邊的count可以一直累加; 又因爲這個原因, 使得dp[target]可以復用.
     * 不如直接看代碼, 不用dp可以看視頻, 不過會超時.
     */
    // dp[i]表示 累積到 '總和(remainingTarget)爲 i' 的 總數
    private int[] dp;

    public int combinationSum4(int[] nums, int target) {
        //这里dp表示 sum到0 的可能性, 只有1中 都不选.
        //dp[1], 有0-1, 1-1, 2中方式, 因为sums不存在`0`元素, 所以, 只能从 0-1. 又因为 数组中只有 1个 1元素, 所以 dp[1] = dp[0] * `1`元素的个数(由sums 有几个1元素决定).
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        //指的是 0-0的可能性? 如果sums 有0 元素呢? leetcode 解释也有点奇怪, 难道 dp[0]== 2? 那 1-1的情况呢.
        //感觉只能当做一个特殊的 base数 去理解.
        dp[0] = 1;
        return helper(nums, target);
    }

    private int helper(int[] nums, int remainingTarget) {
        if (dp[remainingTarget] != -1) {
            return dp[remainingTarget];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (remainingTarget >= nums[i]) {
                // 因爲只要滿足 remainingTarget, 任意的排列組合都是合法的. 所以下邊的 count 可以一直累加
                count += helper(nums, remainingTarget - nums[i]);
            }
        }
        dp[remainingTarget] = count;
        return count;
    }


    /**
     * https://www.bilibili.com/video/BV1V84y1F7Bz?from=search&seid=1746144970453404094
     * 有上邊的dp解法, 可以先不用看這個
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum42(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        //i 是 能到i的意思.
        for (int i = 1; i < comb.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                //target剩余数(例如要到7) 大于等于 sums中第j个元素 时, 将他们的差 累计到 对应comb(DP[i])中
                if (i - nums[j] >= 0) {
                    //能通过 当前的sums[j]元素 来到i的(这个就是dp[i]) dp[j]的可能方法数, 统计出 这些可能数, 就得出到i的 可能数 dp[i].
                    int jStep = i - nums[j];
                    comb[i] += comb[jStep];
                }
            }
        }
        return comb[target];
    }


    /**
     * 會超時!
     */
    // m stores the precomputed result to avoid duplicate computation
    Map<Integer, Integer> remainingTargetCountMap = new HashMap<Integer, Integer>();

    public int combinationSum43(int[] nums, int remainingTarget) {
        if (remainingTarget == 0) return 1;
        else if (remainingTarget < 0) return 0;

        if (remainingTargetCountMap.containsKey(remainingTarget)) return remainingTargetCountMap.get(remainingTarget);

        int res = 0;
        //重點: 這裏一層就是統計某一個 remainingTarget 的所有可能, 因爲每一個都可以使用每一個元素無限次.
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int count = combinationSum43(nums, remainingTarget - num);
            res += count;
        }
        remainingTargetCountMap.put(remainingTarget, res);


        return res;
    }
}
