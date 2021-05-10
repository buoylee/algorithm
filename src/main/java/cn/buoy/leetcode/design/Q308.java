package cn.buoy.leetcode.design;

public class Q308 {
    /**
     * https://www.youtube.com/watch?v=7rDxwVHg_zU
     * <p>
     * https://www.youtube.com/watch?v=WbafSgetDDk
     * 先理解BIT 307 BIT解法(一维BIT), 这里就好做了.
     * BIT(binary index tree) 就是用来解决 前n数sum 的算法. 一维时, 跑一遍BIT就可以生成sum[i]
     */
    public class NumMatrix {

        int[][] tree;
        int[][] nums;
        int m;
        int n;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            m = matrix.length;
            n = matrix[0].length;
            tree = new int[m + 1][n + 1];
            nums = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    //轮到本格, 还需要加上自己的value, 并且随便update 本格开始的
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            if (m == 0 || n == 0) return;
            int delta = val - nums[row][col];
            nums[row][col] = val;
            //关键:
            //i & (-i) 表示 i 的2进制数 的最后一位1 表示的数, 所以更新时, i += i & (-i) 往后更新, 直到越界.
            for (int i = row + 1; i <= m; i += i & (-i)) {
                for (int j = col + 1; j <= n; j += j & (-j)) {
                    tree[i][j] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (m == 0 || n == 0) return 0;
            //右下矩形 + 多减的左上重叠矩形 - 左边矩形 - 上边矩形
            return sum(row2 + 1, col2 + 1) + sum(row1, col1) - sum(row1, col2 + 1) - sum(row2 + 1, col1);
        }

        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= i & (-i)) {
                for (int j = col; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }
    }
// time should be O(log(m) * log(n))
}
