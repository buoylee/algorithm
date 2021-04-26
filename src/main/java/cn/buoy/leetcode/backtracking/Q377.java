package cn.buoy.leetcode.backtracking;

import java.util.Arrays;

public class Q377 {
    /**
     * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     * 注意:leetcode 解释 dp[0]=1, 果然有点奇怪.
     * EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way. Since target == 0 only happens when in the previous call, target = nums[i], we know that this is the only combination in this case, so we return 1.
     * 不好理解, 多看.
     */
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

    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        //假如target 是3, 你要找到 各种 到3的 可能方式; 有可能是 0-3, 1-3, 2-3, 3-3, 所以 任何 到3的可能数 等于 0-3的可能方式, 1-3的可能方式(先找到1,再找2) + 2-3的可能方式 + 3-3可能方式(因为sums中没有元素`0`, 所以不用计算).
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }


    /**
     * https://www.bilibili.com/video/BV1V84y1F7Bz?from=search&seid=1746144970453404094
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
}
