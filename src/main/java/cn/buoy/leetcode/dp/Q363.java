package cn.buoy.leetcode.dp;

import java.util.TreeSet;

public class Q363 {
    /**
     * 看完視頻就懂, 簡單.
     * https://www.bilibili.com/video/BV1w54y1y7Wf?from=search&seid=2005236116826940010
     * 思路: 1. 先利用下边规律, 求出每一格的前缀和.
     * dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r - 1][c - 1]
     * dp[r - 1][c]: [0,0] 到 靠 dp[r][c] 左邊的 的長方形區域.
     * dp[r][c - 1]: [0,0] 到 靠 dp[r][c] 上邊的 的長方形區域.
     * dp[r - 1][c - 1]: dp[r - 1][c] 和 dp[r][c - 1] 重疊的區域.
     * matrix[r - 1][c - 1]: dp[r][c] 本格的值.
     * 注意: dp[r][c] 對應的是 matrix[r - 1][c - 1] 的值.
     * 2. 再使用, dp[r2][c2] - dp[r1 - 1][c2](上) - dp[r2][c1 - 1](左) + dp[r1 - 1][c1 - 1](重叠), 求出 '左上角的點'(r1, c1) 和 '右下角的點'(r2, c2) 的組合而成的長方形.
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length;
        // 細節, 方便計算, 因为使用到 dp[r - 1][c - 1] 前边的格子
        int[][] dp = new int[rows + 1][cols + 1];
        //给`每一格`填充前缀和:
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                // 当前value
                int area = matrix[r - 1][c - 1];
                // 上方一格sums
                area += dp[r - 1][c];
                // 左方一格sums
                area += dp[r][c - 1];
                // 最后减去 上方一格sums, 左方一格sums 重叠的矩阵, 即左上格的矩阵.
                area -= dp[r - 1][c - 1];
                dp[r][c] = area;
            }
        }
        int max = Integer.MIN_VALUE;
        // 遍历每一種 '左上角的點'(r1, c1) 和 '右下角的點'(r2, c2) 的組合而成的長方形, 选出max.
        for (int r1 = 1; r1 <= rows; r1++) { // 左上
            for (int c1 = 1; c1 <= cols; c1++) {
                // 右下
                for (int r2 = r1; r2 <= rows; r2++) {
                    for (int c2 = c1; c2 <= cols; c2++) {
                        int area = dp[r2][c2];
                        area -= dp[r1 - 1][c2];
                        area -= dp[r2][c1 - 1];
                        area += dp[r1 - 1][c1 - 1];
                        if (area <= k)
                            max = Math.max(max, area);
                    }
                }
            }
        }

        //todo 有時間再說; 差别只在, 2分法查找合适k范围.
//        for (int r1 = 0; r1 < rows; r1++) {
//            for (int r2 = r1; r2 < rows; r2++) {
//                TreeSet<Integer> tree = new TreeSet<>();
//                tree.add(0);    // padding
//                for (int c = 0; c < cols; c++) {
//                    int area = areas[r2][c];
//                    if (r1-1 >= 0)
//                        area -= areas[r1-1][c];
//                    Integer ceiling = tree.ceiling(area - k);
//                    if (ceiling != null)
//                        max = Math.max(max, area - ceiling);
//                    tree.add(area);
//                }
//            }
//        }

        return max;
    }
}
