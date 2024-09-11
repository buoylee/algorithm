package cn.buoy.leetcode.matrix;

import java.util.HashSet;

public class Q36 {
    /**
     * https://www.youtube.com/watch?v=iqe1JSjyldo
     * https://www.youtube.com/watch?v=ceOLAY4XUOw 短, 這個寫法更簡單.
     * 思路: 只需要比较, 现有的 "占用 格子的" 数字, 他们之间是否有不合法的放置情况即可. 3種驗證, 行, 列, 3*3塊, 不能有相同的 数字.
     */
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> used = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                if (number != '.')
                    // 關鍵: 把 存在的 "数字 的 格子位置" 直觀转为 存 str.
                    if (!used.add(number + " in row " + i) || // 某行是否有 num
                            !used.add(number + " in col " + j) || // 某列是否有 num
                            !used.add(number + " in block " + i / 3 + "-" + j / 3)) // 某 block 是否有 num; 关键: block 的序号 如何计算, 当作9个大方格就好.
                        return false; // 一旦有冲突就失败
            }
        }
        return true;
    }

    /**
     * 這個代碼巧妙, 但不好理解. 跳過
     */
    public boolean isValidSudoku2(char[][] board) {
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
