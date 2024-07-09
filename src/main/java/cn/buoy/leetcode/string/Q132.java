package cn.buoy.leetcode.string;

public class Q132 {
    /**
     * 看懂思路就簡單, 視頻, 註釋.
     * https://www.youtube.com/watch?v=kTCymFbU2ok&t=7s
     * 思路: dp[i] 表示 從 index 0~i 需要切多少次, 才能使所有 substr 都是迴文.
     * 在確定 index low~high 是迴文的前提下, dp[high] = dp[low - 1] + 1
     */
    public static int minCut(String str) {
        if (str.isEmpty()) return 0;
        int len = str.length();
        // dp[i] 表示 從 index 0~i 需要切多少次, 才能使 substr 都是迴文.
        int[] dp = new int[len];
        // isPal[i][j] 表示 index i~j 是否是迴文. 爲了提高速度做的記憶化.
        boolean[][] isPal = new boolean[len][len];
        // 關鍵: high 從 0~len遍歷, low 在 0~high 之間的點, 保證 low~high 之間爲迴文時, 可知  dp[high] = dp[low - 1] + 1
        // low 表示迴文 左邊界; high 表示迴文 右邊界
        for (int high = 0; high < str.length(); high++) {
            dp[high] = high;
            isPal[high][high] = true;
            //检查后半段是否为回文,
            for (int low = 0; low <= high; low++) {
                // 判斷是否 low, high 間 是否是迴文.
                if (str.charAt(low) == str.charAt(high) // 當前位於 low, high 位置 的 value 是否相同
                        && (high - low <= 1 // 最內層, high - low < 1 表示 奇數迴文; high - low == 1 表示 偶數迴文.
                        || isPal[low + 1][high - 1])) { // low, high 向內一層 是否是迴文.
                    isPal[low][high] = true;
                    //如果 low 等于0 就是 0~high 是迴文, 即 dp[high]
                    if (low == 0)
                        dp[high] = 0;
                    else //如果 low 不是0, 需要分成 0~low-1 和 low~high(迴文), 則 dp[high] 就是 dp[low - 1] + 1
                        dp[high] = Math.min(dp[low - 1] + 1, dp[high]);
                }
            }
        }
        return dp[len - 1];
    }
}
