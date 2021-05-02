package cn.buoy.leetcode.matrix;

public class Q59 {
    /**
     * https://leetcode.com/problems/spiral-matrix-ii/discuss/22289/My-Super-Simple-Solution.-Can-be-used-for-both-Spiral-Matrix-I-and-II
     * https://www.youtube.com/watch?v=HItCSdGVFq4
     * 和 54 没什么区别.
     */
    public int[][] generateMatrix(int n) {
        // Declaration
        int[][] matrix = new int[n][n];

        // Edge Case
        if (n == 0) {
            return matrix;
        }

        // Normal Case
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        int num = 1; //change

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = num++; //change
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++; //change
            }
            colEnd--;

            for (int i = colEnd; i >= colStart; i--) {
                if (rowStart <= rowEnd)
                    matrix[rowEnd][i] = num++; //change
            }
            rowEnd--;

            for (int i = rowEnd; i >= rowStart; i--) {
                if (colStart <= colEnd)
                    matrix[i][colStart] = num++; //change
            }
            colStart++;
        }

        return matrix;
    }
}
