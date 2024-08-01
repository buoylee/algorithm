package cn.buoy.leetcode.dfsandbfs;

public class Q200 {
    /**
     * 和 130 思路一樣, 视频, 代码
     * https://www.youtube.com/watch?v=FqDQxnRctK4
     * 思路: 4個方向dfs, 每到達一個點用#標記, 避免重複遍歷.
     */
    public int numIslands(char[][] grid) {
        int result = 0;
        int rowLen = grid.length;
        if (rowLen == 0) return 0;
        int colLen = grid[0].length;
        // 上到下左到右遍历矩阵格子
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++)
                if (grid[i][j] == '1') {
                    dfs(grid, rowLen, colLen, i, j);
                    // 關鍵: 一次頂層 dfs("没有连起来的 land" 就会是 '1'), 就是一個新島.
                    ++result;
                }
        }
        return result;
    }

    private void dfs(char[][] grid, int rowLen, int colLen, int i, int j) {
        if (i < 0 || j < 0 || i >= rowLen || j >= colLen || grid[i][j] != '1') return;
        // 這個其實直接用 0(水)來替代也可以. 不像130題.
        grid[i][j] = '0';
        dfs(grid, rowLen, colLen, i + 1, j);
        dfs(grid, rowLen, colLen, i - 1, j);
        dfs(grid, i, rowLen, colLen, j + 1);
        dfs(grid, i, rowLen, colLen, j - 1);
    }
}
