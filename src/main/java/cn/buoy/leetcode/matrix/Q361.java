package cn.buoy.leetcode.matrix;

public class Q361 {
    /**
     * https://www.youtube.com/watch?v=WngXEgyX7fU
     */
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = m != 0 ? grid[0].length : 0;
        int result = 0;
        //指 当前点 能炸本行多少敌人
        //为什么这里 不用 arr 缓存, 因为是 一行行遍历的, 所以本行用完, 就可以抛弃.
        int rowhits = 0;
        //指 当前点 能炸本列多少敌人
        //每次移动点都会改变列, 所以可以缓存列 炸的个数, 下次一次来到该列, 如果当前点 不是 M, 则可以`沿用本列` 之前的value.
        int[] colhits = new int[n];
        //遍历每一个点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //如果每一行开头 或 是某一行当前元素为墙.
                if (j == 0 || grid[i][j - 1] == 'W') {
                    //该行命中`敌人数`置0.
                    rowhits = 0;
                    //遍历从j开始的该行, 到墙为止, 记`敌人数`.
                    for (int k = j; k < n && grid[i][k] != 'W'; k++)
                        rowhits += grid[i][k] == 'E' ? 1 : 0;
                }
                //同理, 对每一列 相同操作
                //差别在, 需要保持记录数.
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colhits[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++)
                        colhits[j] += grid[k][j] == 'E' ? 1 : 0;
                }

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
