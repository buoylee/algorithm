package cn.buoy.leetcode.array;

public class Q134 {
    /*
    一旦为负, 则不能继续, 所以 不存在一个在 A, B(AB之间为负) 之间的 x 点, 作为起点, 可以到达 B.
    假如 a => x => b, a 无法到达 b, a + x <= b, 如果x能到达b, 则x > B, 推出 a <= x - B, a 小于 0, 因为a作为起点, 所以a >= 0 , 所以不存在这样的点x.
    如果a->b不通, 则不存在ab间x通b;如果存在.

    https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/
    从图可以看出, 找折线最低点作为起点, 即为答案.

     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (sumGas < sumCost) {
            return -1;
        } else {
            return start;
        }
    }
}
