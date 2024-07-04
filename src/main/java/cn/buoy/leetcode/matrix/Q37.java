package cn.buoy.leetcode.matrix;

public class Q37 {
    /**
     * 簡單, 視頻 ,註釋
     * https://www.youtube.com/watch?v=b6CxF17Y_k4
     * 和 36 類似, 多个backtracking
     * 思路: 給每一個'.'嘗試所有可能(1~9), 檢查, row, col, block 中填入 該數 是否合法, 合法則dfs, 否則 backtracking.
     * 檢查 block 的代碼多看1眼.
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return;
        dfs(board);
    }

    public boolean dfs(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //給每一個'.'嘗試所有可能
                if (board[i][j] == '.') {
                    for (char value = '1'; value <= '9'; value++) {//trial. Try 1 through 9
                        // 合法後插入
                        if (isValid(board, i, j, value)) {
                            board[i][j] = value; //Put c for this cell
                            if (dfs(board))
                                return true; //If it's the solution return true
//                            else // 多餘.
                            board[i][j] = '.'; // backtracking, Otherwise go back
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char value) {
        for (int i = 0; i < 9; i++) {
            // 檢查 col 一整列 是否有 value.
            if (board[i][col] != '.' && board[i][col] == value) return false; //check row
            // 檢查 row 一整還能夠 是否有 value.
            if (board[row][i] != '.' && board[row][i] == value) return false; //check column
            //檢查 所屬的那個 3*3 block中, 是否有 value.
            // 關鍵: 可以想象成 分佈到 3*3 9個大格,
            // 3 * (row / 3) 代表 9個大格的 第幾行,
            //  i / 3 代表 這些行中的 第幾列.
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == value) return false; //check 3*3 block
        }
        return true;
    }
}
