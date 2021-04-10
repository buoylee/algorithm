package cn.buoy.leetcode.dfsandbfs;

public class Q130 {
    /*
    https://leetcode.com/problems/surrounded-regions/discuss/41612/A-really-simple-and-readable-C%2B%2B-solutionuff0conly-cost-12ms
    思路:
    把边缘相关不被包围的O都改为*(DFS遍历边缘坐标), 因为这些不会被X掉, 为的是在将被包围的O变X后,还原回O.
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        int rows = board.length;
        int cols = board[0].length;

        //check first and last col
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O')
                dfs(i, 1, board);
            if (board[i][cols - 1] == 'O')
                dfs(i, cols - 2, board);
        }

        // check first row and last row
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O')
                dfs(1, i, board);
            if (board[rows - 1][i] == 'O')
                dfs(rows - 2, i, board);
        }

        // flip O to X, '*' to 'O',
        // skip the boulders
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
