package cn.buoy.leetcode.dfsandbfs;

public class Q52 {
    /**
     * 看51題就好, 幾乎完全一樣, 與51差別在於, 當發現1個有效結果時, result++即可, 不用記錄具體棋盤.
     * https://www.youtube.com/watch?v=b9G4H00u3ZQ
     * 下邊寫法不太優化, 可以參考51題.
     */
    int res = 0;

    public int totalNQueens(int n) {
        int[][] matrix = new int[n][n];
        doFill(0, matrix);
        return res;
    }

    // dfs
    private void doFill(int rowNum, int[][] matrix) {
        // 最后一行也处理过了, res++
        if (rowNum == matrix.length) {
            res++;
            return;
        }
        for (int col = 0; col < matrix[rowNum].length; col++) {
            if (isValid(rowNum, col, matrix)) {
                matrix[rowNum][col] = 1;
                doFill(rowNum + 1, matrix);
                //backtracking
                matrix[rowNum][col] = 0;
            }
        }
    }

    // 可以優化, 看51題.
    // 当然不用检查行, 这里是一行行遍历的!
    private boolean isValid(int row, int col, int[][] matrix) {
        // 检查`当前点`以往的列
        for (int r = row - 1; r >= 0; r--)
            if (matrix[r][col] == 1)
                return false;
        // 检查`当前点`以往的`\对角线`
        for (int c = col - 1, r = row - 1; c >= 0 && r >= 0; c--, r--)
            if (matrix[r][c] == 1)
                return false;
        // 检查`当前点`以往的`/对角线`
        for (int c = col + 1, r = row - 1; c < matrix[0].length && r >= 0; c++, r--)
            if (matrix[r][c] == 1)
                return false;

        return true;
    }
}
