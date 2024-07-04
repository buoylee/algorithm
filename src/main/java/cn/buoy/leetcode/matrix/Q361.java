package cn.buoy.leetcode.matrix;

public class Q361 {
    /**
     * 能理解, 視頻, 註釋.
     * https://www.youtube.com/watch?v=WngXEgyX7fU
     * https://www.youtube.com/watch?v=CzMYLEoEoRg&t=311s 好
     * 思路: 遍歷 每個點, 當出現 row/col 連續區域邊界時, 完成 當前 連續區域 的 rowhits 和 colhits 的統計,
     * 然後 在boom點, rowhits + colhits 後更新 max 即可.
     */
    public int maxKilledEnemies(char[][] grid) {
        int rowLen = grid.length;
        int colLen = rowLen != 0 ? grid[0].length : 0;
        int result = 0;
        // 表示 行內邊界 與 wall 形成的連續區域內 的当前点, 行方向上 有幾個 enemy.
        // 關鍵: 为什么这里 不用 arr 缓存, 因为以行的方式遍历, 所以只在 本行的連續區域內, 可以做重複使用.
        int rowhits = 0;
        // 当前点 的連續區域內, 本列 enemy 數.
        // 因爲以行的方式遍历, colhits 會重複使用, 所以缓存 列中 enemy 數. 只要沒有遇到 'W'(遇到就需要重新統計).
        int[] colhits = new int[colLen];
        // 遍历 矩陣 每一个点
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                // 只有在出現 '行內邊界/左鄰居是 wall'時, 統計這段 '行內邊界/wall' 形成的連續區域內, enemy 的數量.
                // 是爲了 接下來在這個區域內 計算某boom 總 hits 數(rowhits + colhits[j]) 提供相同的 rowhits.
                if (j == 0 || grid[i][j - 1] == 'W') {
                    // 出現 '邊界/wall' 後重新統計 連續區域內 rowhits(enemy 數量).
                    rowhits = 0;
                    for (int tempColIndex = j; tempColIndex < colLen && grid[i][tempColIndex] != 'W'; tempColIndex++)
                        rowhits += grid[i][tempColIndex] == 'E' ? 1 : 0;
                }
                // 对每一列做 '和行完全相同操作'.
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colhits[j] = 0;
                    for (int tempRowIndex = i; tempRowIndex < rowLen && grid[tempRowIndex][j] != 'W'; tempRowIndex++)
                        colhits[j] += grid[tempRowIndex][j] == 'E' ? 1 : 0;
                }
                // 完成了前置的2步: 統計 rowhits(連續區域 行的 enemy 數) 和 colhits(連續區域 列的 enemy 數), 在boom點, 相加後更新 max 即可.
                if (grid[i][j] == '0')
                    result = Math.max(result, rowhits + colhits[j]);
            }
        }
        return result;
    }


    public int maxKilledEnemies2(char[][] grid) {
        int m = grid.length;
        int n = m != 0 ? grid[0].length : 0;
        int result = 0;
        //
        int[] rowhits = new int[m];
        int[] colhits = new int[n];


        // int[] colhits = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {
                    rowhits[i] = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++)
                        rowhits[i] += grid[i][k] == 'E' ? 1 : 0;
                }
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colhits[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++)
                        colhits[j] += grid[k][j] == 'E' ? 1 : 0;
                }
                if (grid[i][j] == '0')
                    result = Math.max(result, rowhits[i] + colhits[j]);
            }
        }
        return result;
    }
}
