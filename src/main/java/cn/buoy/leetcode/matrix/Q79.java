package cn.buoy.leetcode.matrix;

import java.util.HashSet;

public class Q79 {
    /**
     * 簡單, 典型dfs. 快速視頻, 代碼, 優化了 used map.
     * https://www.youtube.com/watch?v=NOfscYKNpqU 這個可能講的好一點.
     * https://www.youtube.com/watch?v=vNTxFET-Nxk 短代碼, 思路都一樣.
     */
    public boolean exist(char[][] board, String word) {
        // 遍歷矩陣, 如果找到是第一個digit, 就dfs.
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == word.charAt(0))
                    if (dfs(board, i, j, 0, word))
                        return true;
        return false;
    }

    private boolean dfs(char[][] board, int curri, int currj, int currWordIndex, String word) {
        // 末尾
        if (currWordIndex == word.length()) return true;
        if (curri < 0 || curri >= board.length || currj < 0 || currj >= board[0].length // 是否超出 矩陣邊界
//                || visited.contains(curri + "," + currj) // 是否 visited. (看下邊, 優化掉了)
                || board[curri][currj] != word.charAt(currWordIndex)) return false; // 是否等於當前 digit
        char temp = board[curri][currj];
        // 細節: 優化(從 1000+ms 到 100+ms), 不再需要used map, 修改爲 *, 就肯定不會重複選擇這個點.
        board[curri][currj] = '*';
        if (dfs(board, curri + 1, currj, currWordIndex + 1, word) // 右
                || dfs(board, curri - 1, currj, currWordIndex + 1, word) // 左
                || dfs(board, curri, currj + 1, currWordIndex + 1, word) // 上
                || dfs(board, curri, currj - 1, currWordIndex + 1, word)) { // 下
            return true;
        }
        // backtracking
        board[curri][currj] = temp;
        return false;
    }

    /**
     * 太長
     */
    public boolean exist2(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int start = 0;
                if (board[i][j] == word.charAt(start))
                    return dfs(board, word, visited, i, j, start);
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] used, int i, int j, int wordIndex) {
        int m = board.length;
        int n = board[0].length;
        used[i][j] = true;
        if (wordIndex == word.length() - 1)
            return true;
        //右
        if (j < n - 1 && word.charAt(wordIndex + 1) == board[i][j + 1] && !used[i][j + 1]) {
            boolean dfs = dfs(board, word, used, i, j + 1, wordIndex + 1);
            if (dfs)
                return true;
        }
        //左
        if (j > 0 && word.charAt(wordIndex + 1) == board[i][j - 1] && !used[i][j - 1]) {
            boolean dfs = dfs(board, word, used, i, j - 1, wordIndex + 1);
            if (dfs)
                return true;
        }
        //上
        if (i > 0 && word.charAt(wordIndex + 1) == board[i - 1][j] && !used[i - 1][j]) {
            boolean dfs = dfs(board, word, used, i - 1, j, wordIndex + 1);
            if (dfs)
                return true;
        }
        //下
        if (i < m - 1 && word.charAt(wordIndex + 1) == board[i + 1][j] && !used[i + 1][j]) {
            boolean dfs = dfs(board, word, used, i + 1, j, wordIndex + 1);
            if (dfs)
                return true;
        }
        //backtracking
        used[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        Q79 q79 = new Q79();
        // [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
        //        "ABCB"
        char[][] chars = new char[][]{
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'S', 'F', 'C', 'S'},
                new char[]{'S', 'F', 'C', 'S'}
        };
        String str = "ABCB";
        boolean exist = q79.exist(chars, str);
    }
}
