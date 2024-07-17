package cn.buoy.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class Q96 {
    /**
     * 簡單, 視頻, 同 95
     * https://www.youtube.com/watch?v=-rlQCg_TJac
     * 思路: 思路: for (1~n), 取任一個作爲root, 然後遞歸2邊. 但是這題只需要得出可能的組合數量,
     * 能看出 dp[i] 表示的是 i 個 node 可以組成不同的 tree 的數量.
     */
    private Map<Integer, Integer> dp = new HashMap<>();

    public int numTrees(int n) {
        // 不要搞混, 0/1時, dp 是表示 沒有或1個 node 能組成 幾個不同的 tree.
        if (n <= 1)
            return 1;
        if (dp.containsKey(n))
            return dp.get(n);
        int count = 0;
        for (int i = 1; i <= n; i++)
            count += numTrees(i - 1) * numTrees(n - i);
        // memoization
        dp.put(n, count);
        return count;
    }


    /**
     * https://www.youtube.com/watch?v=HWJEMKWzy-Q
     */
    public int numTrees2(int n) {
        int[] G = new int[n + 1];
        G[0] = G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
