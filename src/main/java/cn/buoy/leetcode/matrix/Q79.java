package cn.buoy.leetcode.matrix;

public class Q79 {
    public static void main(String[] args) {
        Q79 q79 = new Q79();

//[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
//        "ABCB"
        char[][] chars = new char[][]{
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'S', 'F', 'C', 'S'},
                new char[]{'S', 'F', 'C', 'S'}
        };
        String str = "ABCB";
        boolean exist = q79.exist(chars, str);
    }

    /**
     * 快速回顾: https://www.youtube.com/watch?v=NOfscYKNpqU
     */
    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int start = 0;
                if (board[i][j] == word.charAt(start)) {
//                    boolean dfs = dfs(board, word, visited, i, j, start);
//                    if (dfs)
//                        return true;
                    if (!dfs(board, word, visited, i, j, start)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] visited, int i, int j, int wordIndex) {
        int m = board.length;
        int n = board[0].length;


        visited[i][j] = true;
        if (wordIndex == word.length() - 1)
            return true;
        //右
        if (j < n - 1 && word.charAt(wordIndex + 1) == board[i][j + 1] && !visited[i][j + 1]) {
            boolean dfs = dfs(board, word, visited, i, j + 1, wordIndex + 1);
            if (dfs)
                return true;
        }
        //左
        if (j > 0 && word.charAt(wordIndex + 1) == board[i][j - 1] && !visited[i][j - 1]) {
            boolean dfs = dfs(board, word, visited, i, j - 1, wordIndex + 1);
            if (dfs)
                return true;
        }
        //上
        if (i > 0 && word.charAt(wordIndex + 1) == board[i - 1][j] && !visited[i - 1][j]) {
            boolean dfs = dfs(board, word, visited, i - 1, j, wordIndex + 1);
            if (dfs)
                return true;
        }
        //下
        if (i < m - 1 && word.charAt(wordIndex + 1) == board[i + 1][j] && !visited[i + 1][j]) {
            boolean dfs = dfs(board, word, visited, i + 1, j, wordIndex + 1);
            if (dfs)
                return true;
        }
        //backtracking
        visited[i][j] = false;

        return false;
    }
}
