package cn.buoy.leetcode.dfsandbfs;

public class Q130 {
    /**
     * 看視頻, 代碼用下邊的比較好寫. 好想也比較好寫.
     * https://leetcode.com/problems/surrounded-regions/discuss/41612/A-really-simple-and-readable-C%2B%2B-solutionuff0conly-cost-12ms
     * https://www.youtube.com/watch?v=LzPndif-j4k
     * 思路: dfs, 先找到 board 4個邊緣 是 O 的點(肯定不會被吃掉的), 標記爲 *,
     * 然後從 這些點 開始, 4個方向(上下左右)做 dfs, 4條邊做完後, 剩下的 O 就是會被吃掉的(無法和邊緣 * 連通),
     * 最後把 O 改成 X, * 改回 O.
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int rows = board.length;
        int cols = board[0].length;
        // check first and last col
        // 从 左右邊界2列的格子 开始檢查
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O')
                // 细节: 因为给个起始点都会被检查, 所以 优化成 "向内一步" 开始 dfs.
                dfs(i, 1, board);
            if (board[i][cols - 1] == 'O')
                dfs(i, cols - 2, board);
        }
        // check first row and last row
        // 檢查上下邊界2行
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O')
                dfs(1, i, board);
            if (board[rows - 1][i] == 'O')
                dfs(rows - 2, i, board);
        }
        // flip O to X, '*' to 'O',
        // skip the boulders
        // 把矩阵中所有標記 '*' 的(不被吃掉的)改回 'O'; 還是 'O' 的, 则改成 'X'
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == '*')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    public void dfs(int i, int j, char[][] board) {
        // i, j == 0 的點 就是邊界, 作爲起始點肯定會被檢查, 可以跳過.
        if (i <= 0 || j <= 0 || i >= board.length - 1 || j >= board[0].length - 1 || board[i][j] == 'X')
            return;
        if (board[i][j] == '*')
            return;
        if (board[i][j] == 'O')
            board[i][j] = '*';
        dfs(i + 1, j, board);
        dfs(i - 1, j, board);
        dfs(i, j + 1, board);
        dfs(i, j - 1, board);
    }
}
