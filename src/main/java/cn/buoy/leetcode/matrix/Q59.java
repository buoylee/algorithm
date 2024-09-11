package cn.buoy.leetcode.matrix;

public class Q59 {
    /**
     * 巨簡單, 比 54 還簡單. 視頻.
     * https://leetcode.com/problems/spiral-matrix-ii/discuss/22289/My-Super-Simple-Solution.-Can-be-used-for-both-Spiral-Matrix-I-and-II
     * https://www.youtube.com/watch?v=HItCSdGVFq4
     * 思路: 每走完一行/列, 修改限制(不能走到之前的行/列). 因爲是正方形, 不需要 多餘的判斷邊界.
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if (n == 0) return matrix;
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        // 從 1 開始填.
        int num = 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++)
                matrix[rowStart][i] = num++; //change
            rowStart++;
            for (int i = rowStart; i <= rowEnd; i++)
                matrix[i][colEnd] = num++; //change
            colEnd--;
            for (int i = colEnd; i >= colStart; i--) {
                // 关键: 其实不再需要判断 // todo
                if (rowStart <= rowEnd)
                    matrix[rowEnd][i] = num++; //change
            }
            rowEnd--;
            for (int i = rowEnd; i >= rowStart; i--) {
                // 同理不需要判断
                if (colStart <= colEnd)
                    matrix[i][colStart] = num++; //change
            }
            colStart++;
        }
        return matrix;
    }
}
