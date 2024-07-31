package cn.buoy.leetcode.backtracking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q377 {
    /**
     * 视频是dp, 其实没有 backtracking 直观, 看代码.
     * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     * EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way. Since target == 0 only happens when in the previous call, target = nums[i], we know that this is the only combination in this case, so we return 1.
     * https://www.youtube.com/watch?v=JFB0HZ5DcSw
     * 思路: 遍历 ele, 得到不同 remain = target - nums[i], 将 remain 传入下层 dfs, 同样方式, 直到 remain == 0;
     * 每个 dfs, 表示的是, remain 的 所有组个数, 也就是 dp[main];
     * 每个 dfs 中, 有 "nums.len 种" 拆分方式(remain - nums[i]), 统计 这些 dp[remain - nums[i]], 就是 dp[main];
     * 注意: 關鍵, 因爲只要滿足target, 任意元素的排列組合都是合法的. 所以下邊的 count 可以一直累加; 又因爲這個原因, 使得dp[target]可以復用.
     */
    public int combinationSum4(int[] nums, int target) {
        //dp[i] 表示, sum == i时, 有几种组合方式.
        //dp[1], 有0-1, 1-1, 2中方式, 因为sums不存在`0`元素, 所以, 只能从 0-1. 又因为 数组中只有 1个 1元素, 所以 dp[1] = dp[0] * `1`元素的个数(由sums 有几个1元素决定).
        int[] dp = new int[target + 1];
        // -1 表示 还没 统计过
        Arrays.fill(dp, -1);
        // 只有 "一个 num 都不选" 这1种方式
        dp[0] = 1;
        return helper(dp, nums, target);
    }

    // 统计 dp[remain]的 组合个数
    private int helper(int[] dp, int[] nums, int remain) {
        // 没统计过就是 -1; 统计过, 但真的没有, 就是 0;
        if (dp[remain] != -1)
            return dp[remain];
        int count = 0;
        // 题目表示, 只要滿足 remain, 任意的排列組合都是合法的.
        for (int i = 0; i < nums.length; i++)
            if (remain >= nums[i])
                // 关键: 遍历所有 remain = "当前 nums[i]" + (remain - "当前 nums[i]") 的可能, 统计其中的 dp[remain - "当前 nums[i]"], 就是 dp[remain].
                count += helper(dp, nums, remain - nums[i]);
        dp[remain] = count;
        return count;
    }


    /**
     * https://www.bilibili.com/video/BV1V84y1F7Bz?from=search&seid=1746144970453404094
     * 有上邊的dp解法, 可以先不用看這個
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
