package cn.buoy.leetcode.dfsandbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q51 {
    /**
     * 簡單, 视频听思路, 寫法要小心.
     * https://www.youtube.com/watch?v=Xa-yETqFNEQ
     * 思路: backtracking, 遍歷每一行的放置, 放置後, 記錄"米"字4個方向的佔用情況(我們遍歷的是行, 所以可以省略行這個方向上的記錄), dfs, backtrack, 如此遞歸回溯下去.
     * 关键: 计算 2条对角线所属的 index
     */
    public List<List<String>> solveNQueens(int n) {
        // 模擬棋盤
        char[][] tempBoard = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tempBoard[i][j] = '.';
        List<List<String>> result = new ArrayList<>();
        // 表示 第n列 是否有 queen 占用
        int[] cols = new int[n];
        // 关键: 表示 遞增方向 对角线 上的格子 是否有 queen 占用, "2 * n - 1" 可以通过观察到, 实际上是由 一边长度 + (一边长度 - 1) 而来
        int[] mainDiagonal = new int[2 * n - 1];
        // 表示 遞減方向 对角线 上的格子 是否有 queen 占用
        int[] antiDiagonal = new int[2 * n - 1];
        dfs(0, tempBoard, result, cols, antiDiagonal, mainDiagonal);
        return result;
    }

    /**
     * 逐列找合適的位置插入queen, 到了最後一列, 返回
     *
     * @param currCol       表示 当前遍历到 第n列
     * @param tempBoard
     * @param res
     * @param cols          表示第n列格子是否有 queen, 0没有/1有
     * @param mainDiagonals 遞增方向上的格子是否有 queen
     * @param antiDiagonals 遞減方向上的格子是否有 queen
     */
    static void dfs(int currCol, char[][] tempBoard, List<List<String>> res, int[] cols, int[] mainDiagonals, int[] antiDiagonals) {
        if (currCol == tempBoard.length) {
            res.add(buildStrList(tempBoard));
            return;
        }
        // 每列都要 從0行到尾行 檢查.
        for (int row = 0; row < tempBoard.length; row++) {
            // 關鍵: 該 cols[] 列的格子; mainDiagonals[] 遞增方向上的格子; antiDiagonals[] 遞減方向上的格子; 是否被佔用.
            // 关键: row + currCol 和 board.length - 1 + currCol - row 用數字代進去, 算出來的. 2元1次方程.
            // row + currCol: 方程: y = -x; (0, 0) 为 index0
            // board.length - 1 + currCol - row: 方程: y = x + (len - 1); (7, 0) 为 index0
            if (cols[row] == 0 && mainDiagonals[row + currCol] == 0 && antiDiagonals[tempBoard.length - 1 + currCol - row] == 0) {
                // 放置Q, 更新所属 列/正对角线/反对角线占用.
                // 放真的 str 'Q', 是题目最后要输出棋盘状态
                tempBoard[row][currCol] = 'Q';
                cols[row] = 1;
                mainDiagonals[row + currCol] = 1;
                antiDiagonals[tempBoard.length - 1 + currCol - row] = 1;
                dfs(currCol + 1, tempBoard, res, cols, mainDiagonals, antiDiagonals);
                //回溯
                tempBoard[row][currCol] = '.';
                cols[row] = 0;
                mainDiagonals[row + currCol] = 0;
                antiDiagonals[tempBoard.length - 1 + currCol - row] = 0;
            }
        }
    }

    /**
     * 把 char[][] 構建成 List<String> 返回結果.
     */
    static List<String> buildStrList(char[][] board) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            // 一整行 int[] 轉爲 str.
            String s = new String(board[i]);
            result.add(s);
        }
        return result;
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
