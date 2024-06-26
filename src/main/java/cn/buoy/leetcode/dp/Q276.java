package cn.buoy.leetcode.dp;

public class Q276 {
    /**
     * https://www.youtube.com/watch?v=XL1icaNQCa4
     * https://www.youtube.com/watch?v=rxbZlCfazrk&t=2s 比較短. 不理解的話, 就先看註釋. 不行再看上邊視頻.
     * 下邊寫法是容易理解的
     * 思路: dp[i] 只需要瞭解 dp[i - 1] 和 dp[i - 2]. 看下邊.
     */
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;

        // 因爲允許最多2個相鄰相同. 所以都可以任選.
        if (n == 2) return k * k;

        // 關鍵: dp[i] 表示, 塗了i個post, 共有幾種塗法.
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k * k;

        for (int i = 3; i <= n; i++) {
            // 關鍵: dp[i - 1] * (k - 1), 表示 'i' 與 'i-1' 顏色不同 的可能數量.
            // 關鍵: dp[i - 2] * (k - 1), 表示 'i' 和 'i-1' 顏色選擇相同, 那這裏就需要 'i' 不同與 'i-2' 的可能數量. 直觀的算法應該是 dp[i - 2] * (k - 1) * 1, 所以下邊只是簡化的寫法.
            dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1); // dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1) * 1;
        }
        return dp[n];
    }


    /**
     * 這裏註釋寫的有問題, 應該, 先不用看.
     * 思路: dp[n] 是由 index i 色 ==  index i-1 色 , 和 i 色 !=  index i-1 色 的可能 的和.
     * dp[i] = dp_same[i-1] + dp_diff[i-1]
     * dp_same[i], 意义是 i 的颜色 == i-1 的颜色, dp_diff[i], 意义是 i =! i-1 的颜色.
     * 如果 dp_same[i], 则 dp_diff[i-1], 即 i-2 顏色 != i-1 顏色. dp_same[i] == dp_diff[i-1]
     * 如果 dp_diff[i], 则 i-2 可以是任何色(可以和 i-1 相同或不同), 即 dp_diff[i-1]*(k-1) + dp_same[i-1]*(k-1).
     */
    public int numWays2(int n, int k) {
        if (n == 0) return 0;
        else if (n == 1) return k;
        int diffColorCounts = k * (k - 1);
        int sameColorCounts = k;
        for (int i = 2; i < n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }


}
