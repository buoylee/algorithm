package cn.buoy.leetcode.matrix;

public class Q329 {
    /**
     * 難點在, 想通, 其實 是從 不同終點(最大值)往回蔓延(前一步+1)同一個點.
     * https://www.youtube.com/watch?v=yZGpDJlcxRA
     * 思路: 就dfs, dp從終點開始直到起點.
     */
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int rowLen = matrix.length, colLen = matrix[0].length;
        // dp[i][j] 表示 i, j 這個點出發, 能構成最長遞增序列的長度.
        int[][] dp = new int[rowLen][colLen];
        int max = 1;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                int len = dfs(matrix, i, j, rowLen, colLen, dp);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    // dfs 實際是從末尾運算到頭.
    public int dfs(int[][] matrix, int i, int j, int rowLen, int colLen, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];
        // dp 的最小可能的值(無法遞增, 就他本身一個元素).
        int max = 1;
        // 上下左右 尋找遞增元素.
        for (int[] dir : dirs) {
            int incI = i + dir[0], incJ = j + dir[1];
            // 不需要記錄 used, 因爲有遞增這個特性.
            if (incI < 0 || incI >= rowLen || incJ < 0 || incJ >= colLen // 矩陣邊界外
                    || matrix[incI][incJ] <= matrix[i][j]) // 元素不遞增
                continue;
            // 取 所有可能長度的 最大值.
            max = Math.max(max, dfs(matrix, incI, incJ, rowLen, colLen, dp) + 1);
        }
        dp[i][j] = max;
        return max;
    }
}
