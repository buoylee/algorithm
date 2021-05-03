package cn.buoy.leetcode.matrix;

import java.util.HashSet;

public class Q36 {
    /**
     * https://www.youtube.com/watch?v=iqe1JSjyldo
     * 思路一样, 代码更精简.
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9; j++) {
                //一行行 遍历
                if (board[i][j] != '.' && !rows.add(board[i][j]))
                    return false;
                //一列列 遍历
                if (board[j][i] != '.' && !columns.add(board[j][i]))
                    return false;
                //数独 3*3 内 不同 判断, 看成9个大正方形
                //归类到 9个大正方形 中.
                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                if (board[RowIndex + j / 3][ColIndex + j % 3] != '.' && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
                    return false;
            }
        }
        return true;
    }
}
