package cn.buoy.leetcode.dp;

import java.util.TreeSet;

public class Q363 {
    /**
     * https://www.bilibili.com/video/BV1w54y1y7Wf?from=search&seid=2005236116826940010
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] areas = new int[rows][cols];
        //给`每一格`填充前缀和:
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                //当前value
                int area = matrix[r][c];
                //上方一格sums
                if (r - 1 >= 0)
                    area += areas[r - 1][c];
                //左方一格sums
                if (c - 1 >= 0)
                    area += areas[r][c - 1];
                //最后减去 上方一格sums, 左方一格sums 重叠的矩阵, 即左上格的矩阵.
                if (r - 1 >= 0 && c - 1 >= 0)
                    area -= areas[r - 1][c - 1];
                areas[r][c] = area;
            }
        }
        int max = Integer.MIN_VALUE;
        //遍历每一种情况, 选出max.
        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                for (int r2 = r1; r2 < rows; r2++) {
                    for (int c2 = c1; c2 < cols; c2++) {
                        int area = areas[r2][c2];
                        if (r1 - 1 >= 0)
                            area -= areas[r1 - 1][c2];
                        if (c1 - 1 >= 0)
                            area -= areas[r2][c1 - 1];
                        if (r1 - 1 >= 0 && c1 - 1 >= 0)
                            area += areas[r1 - 1][c1 - 1];
                        if (area <= k)
                            max = Math.max(max, area);
                    }
                }
            }
        }

        //差别只在, 2分法查找合适k范围.
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
