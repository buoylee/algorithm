package cn.buoy.leetcode.dp;

public class Q265 {
    /**
     * 256: https://www.youtube.com/watch?v=K-G6pMiwb_k
     * https://www.youtube.com/watch?v=KVfQmGiwEMU (需要會員)
     * https://www.youtube.com/watch?v=a6rDSwdW2Mo 当题目看, 然後看註釋. 算簡單.
     * 思路: 要得到 dp[i][j], 就要找出 min(dp[i-1][非j]) + costs[i][j].
     * 具体实现, 只需要记录 i - 1 房子 cost 最小的 2种颜色 即可, 因为 肯定 i 房子 如何选 都可以 在这2者中选出 与自己不同的颜色 保持最小 cost 的可能(dp[i][j]).
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int houseLen = costs.length;
        int colorLen = costs[0].length;
        // 當前房子 "塗色 cost 最小和次小" 的顏色的 index
        // 關鍵: 只需要記錄, 当前房子的 "最小和次小 cost 顏色" 即可. 因为 下一个房子的颜色, 不和 当前房子 颜色相同 即可.
        int currMin1 = -1;
        int currMin2 = -1;
        // 遍历房子
        for (int i = 0; i < costs.length; i++) {
            // 上個房子塗色最小和次小顏色的index
            int lastMin1 = currMin1;
            int lastMin2 = currMin2;
            // 爲選出 當前房子 最小次小顏色index 初始化.
            currMin1 = -1;
            currMin2 = -1;
            // 遍历当前房子颜色
            for (int j = 0; j < colorLen; j++) {
                // 表示當前j顏色不是上一個房子最小花費的顏色(lastMin1)
                if (j != lastMin1) {
                    // 關鍵: lastMin1 == -1 表示 当前在第一间房子, 前邊爲空, 用 -1 表示不存在.
                    // 關鍵: 注意, 這裏 costs[i][j] 已經當作 dp 來使用(用來累加).
                    costs[i][j] += lastMin1 == -1 ? 0 : costs[i - 1][lastMin1];
                } else  // 上一個最小的顏色(lastMin1)和當前j顏色相同, 那只能配上 上一個房子次小花費的顏色(lastMin2).
                    costs[i][j] += lastMin2 == -1 ? 0 : costs[i - 1][lastMin2];
                // 關鍵: 找出 當前房子 最小和次小的 顏色index.
                if (currMin1 == -1 || costs[i][j] < costs[i][currMin1]) { // currMin1 == -1 理解爲 開始第一次爲這個房子挑顏色;
                    currMin2 = currMin1; // 发现比 最小的 还小, 先赋值给 currMin2, 再把 新值 付给 currMin1.
                    currMin1 = j;
                } else if (currMin2 == -1 || costs[i][j] < costs[i][currMin2]) // 不是 最小, 再考虑 currMin2
                    currMin2 = j;
            }
        }
        return costs[houseLen - 1][currMin1];
    }


    /**
     * 思路一樣, 實現稍不同.
     */
    public int minCostII2(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int houseLen = costs.length, colorLen = costs[0].length;

        //dp[i][j] indicate the optimal cost for the house i if it is painted with color j.
        // dp[i][j], `i房子`涂`j颜色`的情况下, 的最小花费
        int[][] dp = new int[houseLen][colorLen];
        //初始化第一间房
        for (int j = 0; j < colorLen; j++)
            dp[0][j] = costs[0][j];

        //从第2间房开始
        for (int i = 1; i < houseLen; i++) {
            // 關鍵: 如果i房使用j颜色, 那dp[i][j]最少花费就是, 前一個房子(i-1)的 其他顏色(除了j)中 最小的那個dp, 即(minCost(dp[i - 1], j)).
            for (int j = 0; j < colorLen; j++) {
                dp[i][j] = minCost(dp[i - 1], j) + costs[i][j];
            }
        }
        return minCost(dp[houseLen - 1], -1);
    }


    /**
     * 找到 涂到 i - 1 房子为止, i-1 房子顏色 不同于j顏色 的最小dp.
     */
    //find the minimum cost if the current house is painted with different color except j.
    //if j == -1, then find minimum cost for the current house comparing different color.
    private int minCost(int[] dp, int j) {
        int min = Integer.MAX_VALUE;
        int i = 0;
        while (i < dp.length) {
            // 前邊 和 當前 顏色相同, 不用比
            if (j != -1 && i == j)
                ;
            else
                min = Math.min(min, dp[i]);
            i++;
        }
        return min;
    }
}
