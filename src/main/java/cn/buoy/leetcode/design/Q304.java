package cn.buoy.leetcode.design;

public class Q304 {
    /**
     * 超簡單, 視頻.
     * https://www.youtube.com/watch?v=MSNSqU3BnXk
     * https://www.youtube.com/watch?v=0F6gRjMx6RE 短
     * 思路: 矩形加減 和 重疊
     */
    public class NumMatrix {
        private int[][] prefixSums;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            int m = matrix.length, n = matrix[0].length;
            // 使得 如果左上角是 [0,0], 計算方便
            prefixSums = new int[m + 1][n + 1];
            // 計算 格子 到 左上[0,0] 的 sum
            for (int i = 1; i <= m; i++)
                for (int j = 1; j <= n; j++)
                    prefixSums[i][j] = matrix[i - 1][j - 1] // 當前 value
                            + prefixSums[i - 1][j] // dp[上]
                            + prefixSums[i][j - 1] // dp[左]
                            - prefixSums[i - 1][j - 1]; // 左上這一塊 是由 上 和 左 的重疊部分, 所以要減取一個
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefixSums[row2 + 1][col2 + 1] // 右下角點
                    - prefixSums[row1][col2 + 1] // 右上角 的點 形成的 矩形
                    - prefixSums[row2 + 1][col1] // 左下角 的點 形成的 矩形
                    + prefixSums[row1][col1]; // 這2個矩形 重疊 區域.
        }
    }

    class NumMatrix2 {

        private int[][] dp;

        public NumMatrix2(int[][] matrix) {
            if (matrix == null
                    || matrix.length == 0
                    || matrix[0].length == 0) {
                return;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    //关键代码: (0,0) -> (i - 1, j)的左边矩形 + ...上边矩形 - 左, 上矩形 重复部分(0,0)->(i - 1, j - 1) + 当前点value
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int iMin = Math.min(row1, row2);
            int iMax = Math.max(row1, row2);

            int jMin = Math.min(col1, col2);
            int jMax = Math.max(col1, col2);
            //看图!就懂!
            //原理和上边一样!
            return dp[iMax + 1][jMax + 1] - dp[iMax + 1][jMin] - dp[iMin][jMax + 1] + dp[iMin][jMin];
        }
    }
}


