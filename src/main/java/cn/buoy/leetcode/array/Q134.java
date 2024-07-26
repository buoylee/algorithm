package cn.buoy.leetcode.array;

public class Q134 {
    /**
     * 懂得思路和证明就简单, 视频
     * https://www.youtube.com/watch?v=bkXokc5hh14
     * 思路: 先弄清楚一个证明: 如果以 a 作为起点, 经历多个 node 后, 在 f 点, 发现无法走到下一个点 g, 那是否在 "a -> g 之间" 存在一点作为起点, 可以完整绕一圈呢? 不存在.
     * 假设:
     * a -> pre -> g
     * 可知前提, a -> b: gas < cost; pre -> g: pre-gas > pre-cost;
     * 所以: a -> pre: gas - pre-gas 必然 < cost - pre-cost
     * 到这里, 我们代码可以简化为, 遍历 station 时, 不断 累积 gas 和 cost, 只要一旦在 i 发现 sum(gas) < sum(cost), 我们只能从 i + 1 的位置, 重新作为起点, 来判断是否能 走完一整圈.
     * 又因为, 我们从刚开始, 就一直计算剩余油量, 如果, 到达最后, 还有油剩余, 则可以走完全程. (?)
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 全程累计 gas
        int sumGas = 0;
        // 全程累计 cost
        int sumCost = 0;
        // 起点
        int result = 0;
        // 由设定的起点开始算, 是否够油到达 next station.
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            // 关键: 由设定的起点开始算的剩余油量, 一旦为负, 无法到达 next station.
            tank += gas[i] - cost[i];
            if (tank < 0) {
                // 以 next station 作为起点.
                result = i + 1;
                // 以 "当前 node" 为起点的 剩余油量 重新计算, 重置.
                tank = 0;
            }
        }
        // 如果 全程 sum(gas) >= 全程 sum(cost)
        return sumGas >= sumCost ? result : -1;
        // 因爲到最後 tank == sumGas - sumCost, 結果必定與最後if (sumGas < sumCost)互斥, 不需要 % gas.length(答案出现在前边元素)
//            return result % gas.length;
    }

    /**
     * https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/
     * 从图可以看出, 找折线最低点作为起点, 即为答案.
     */
}
