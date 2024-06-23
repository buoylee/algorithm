package cn.buoy.leetcode.dfsandbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q51 {

    /**
     * https://www.youtube.com/watch?v=Xa-yETqFNEQ
     * 先看vod, 思路其實是簡單的, 寫法要小心.
     * 回溯是最容易想到的.
     * 思路: 遍歷每一行的放置, 放置後, 記錄"米"字4個方向的佔用情況(我們遍歷的是行, 所以可以省略行這個方向上的記錄), 遞歸, 清除記錄, 如此遞歸回溯下去.
     */
    public List<List<String>> solveNQueens(int n) {
        // 模擬棋盤
        char[][] tempBoard = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tempBoard[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        // 第n行
        int[] leftRow = new int[n];
        // 遞增方向上的格子
        int[] mainDiagonal = new int[2 * n - 1];
        // 遞減方向上的格子
        int[] antiDiagonal = new int[2 * n - 1];
        backtrack(0, tempBoard, res, leftRow, antiDiagonal, mainDiagonal);
        return res;
    }

    /**
     * 逐列找合適的位置插入queen, 到了最後一列, 返回
     *
     * @param col          表示第n列
     * @param tempBoard
     * @param res
     * @param leftRow      表示第n行
     * @param mainDiagonal 遞增方向上的格子
     * @param antiDiagonal 遞減方向上的格子
     */
    static void backtrack(int col, char[][] tempBoard, List<List<String>> res, int[] leftRow, int[] mainDiagonal, int[] antiDiagonal) {
        if (col == tempBoard.length) {
            res.add(construct(tempBoard));
            return;
        }

        // 每列都要 從0行到尾行 檢查.
        for (int row = 0; row < tempBoard.length; row++) {
            // 關鍵: 該 row 行的格子; mainDiagonal[] 遞增方向上的格子; antiDiagonal[] 遞減方向上的格子; 是否被佔用.
            // row + col 和 board.length - 1 + col - row 用數字代進去, 算出來的.2元1次方程.
            if (leftRow[row] == 0 && mainDiagonal[row + col] == 0 && antiDiagonal[tempBoard.length - 1 + col - row] == 0) {

                // 佔用
                tempBoard[row][col] = 'Q';
                leftRow[row] = 1;
                mainDiagonal[row + col] = 1;
                antiDiagonal[tempBoard.length - 1 + col - row] = 1;

                backtrack(col + 1, tempBoard, res, leftRow, mainDiagonal, antiDiagonal);

                //回溯
                tempBoard[row][col] = '.';
                leftRow[row] = 0;
                mainDiagonal[row + col] = 0;
                antiDiagonal[tempBoard.length - 1 + col - row] = 0;
            }
        }
    }

    /**
     * 把 char[][] 構建成 List<String> 返回結果.
     */
    static List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            // 一整行 int[] 轉爲 str.
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }


    /**
     * 思路一樣, 跳過, 而且這個寫法相對不好理解.
     */
    public List<List<String>> solveNQueens2(int n) {
        //外层: 一共x种解法; 内层n 行, string 一行表示
        List<List<String>> res = new ArrayList<>();
        String[] queens = new String[n];
        char[] initial = new char[n];
        Arrays.fill(initial, '.');
        Arrays.fill(queens, String.valueOf(Arrays.copyOf(initial, n)));
        int[] flag = new int[5 * n - 2];
        Arrays.fill(flag, 1);
        slove(res, queens, flag, 0, n);
        return res;
    }

    //fixme
    //y = x, y = x - 1, /
    //y = -x, y = -x - 1, \
    private void slove(List<List<String>> res, String[] queens, int[] flag, int row, int n) {
        if (row == n) {
            res.add(new ArrayList<>(Arrays.asList(queens)));
            return;
        }
        for (int col = 0; col != n; col++) {
            if (flag[col] == 1 && flag[n + col + row] == 1 && flag[4 * n - 2 + col - row] == 1) {
                flag[col] = 0;
                flag[n + col + row] = 0;
                flag[4 * n - 2 + col - row] = 0;
                char[] chars = queens[row].toCharArray();

                chars[col] = 'Q';
                queens[row] = String.valueOf(chars);

                slove(res, queens, flag, row + 1, n);

                chars = queens[row].toCharArray();
                chars[col] = '.';
                queens[row] = String.valueOf(chars);

                flag[col] = 1;
                flag[n + col + row] = 1;
                flag[4 * n - 2 + col - row] = 1;
            }
        }
    }
}
