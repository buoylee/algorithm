package cn.buoy.leetcode.dfsandbfs;

public class Q200 {
    private int n;
    private int m;

    /**
     * https://www.youtube.com/watch?v=FqDQxnRctK4
     * 和 130 思路一樣, 4個方向dfs, 每到達一個點用#標記, 避免重複遍歷.
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    // 關鍵: 一次頂層dfs, 就是一個島.
                    ++count;
                }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        // 這個其實直接用 0(水)來替代也可以. 不像130題.
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
}
