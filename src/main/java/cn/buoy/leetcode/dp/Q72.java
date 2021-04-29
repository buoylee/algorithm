package cn.buoy.leetcode.dp;

public class Q72 {
    /**
     * https://www.youtube.com/watch?v=Uv9dNpHlSY4
     * 视频 讲的不错, 容易理解. 代码实现 稍有不同, 理解思路就好.
     * 思路:
     * dp[i][j]定义: i 为 原str index = i , j 为 target str index = j,
     * dp[i][j] 表示, 原str 0-i 的substr 变化到 target str 0-j 的substr, 最少需要多少步.
     * 关键: 其实取的是目标格 的 左, 右, 左上, 这3中one step情况的 min 然后++.
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        //其中一str为空, 另一str len时, 则需要 空 add 相同 len 步, 或 remove 相同 len 步 为空.
        //下列分别对应上述2种情况.
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int i = 1; i <= n; i++)
            dp[0][i] = i;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //这步好理解, 如果前word1, 2 用 n 步完成, 且 当前双方都 加的char 又相同, 那就没有增加需要处理的 步数.
                if (word1.charAt(i) == word2.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j];
                else {
                    //双方新加的char 不相同时, 3类情况,
                    //只有原str 多1 char, 只有target str 多1 char, 都多1 char.
                    //原str多, remove; target str多, add; 同时增加char, 且不同(相同的情况, 在上方, 用最后一个char是否相同的方式 处理了, 注意理解, 与这里其实不同的理解.), replace.
                    // 都是 one step 的操作. 所以 ++.
                    int a = dp[i][j];
                    int b = dp[i][j + 1];
                    int c = dp[i + 1][j];
//                    dp[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
//                    dp[i + 1][j + 1]++;
                    dp[i + 1][j + 1] = Math.min(Math.min(a, b), c) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
