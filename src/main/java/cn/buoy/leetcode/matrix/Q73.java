package cn.buoy.leetcode.matrix;

public class Q73 {
    /**
     * https://www.youtube.com/watch?v=-I8w2_sN93c
     * 思路, 遍历每一个格子(x, y), 如果是0, 将对应的(x, 0), (0,y) 也置为0,
     * 第2次遍历第一行/列, 只要出现0 就将 整行/列 置0,
     * 注意! 因为我们将第一行 第一列作为了 辅助使用, 所以 当其出现0的时候, 会将 之前的标记都破坏, 所以要将这2个情况放在最后(大于1的行/列 都置0后)再处理.
     */
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false, firstColumn = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) firstRow = true;
                    if (j == 0) firstColumn = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        //skip过 第1行/列
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //现在才处理
        if (firstRow) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstColumn) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

    }
}
