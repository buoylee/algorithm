package cn.buoy.leetcode.design;

public class Q308 {
    /**
     * Binary Indexed Tree 算法懂了就簡單,
     * 先理解BIT 307 BIT解法, 这里就好做了.
     * https://www.youtube.com/watch?v=7rDxwVHg_zU
     * https://www.youtube.com/watch?v=WbafSgetDDk 如何構建 Binary Indexed Tree
     * 關鍵:
     * 構建 Binary Indexed Tree: 求 BIT i 的 parent: i + lowbit(i); lowbit 求的是 i 的最低位的1的值, 例: 10100, lowbit == 100, 也就是 4; lowbit 可以用 "x & (-x)" 得到. -x = ~x + 1
     * query Binary Indexed Tree: 求前 i 個的 sum, i -= lowbit(i), 累加這些結果, 直到 i == 0. 就是答案.
     * 思路: 關鍵就在構建 Binary Indexed Tree,
     * 後邊就是用構建好的樹, 利用 "右下矩形 - 左边矩形 - 上边矩形 + 多减的左上重叠矩形" 就是 目標矩形的 sum, 超簡單.
     */
    public class NumMatrix {
        int[][] tree; // Binary Indexed Tree
        int[][] nums;
        int m;
        int n;

        // 構建 Binary Indexed Tree
        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            m = matrix.length;
            n = matrix[0].length;
            // 因爲 Binary Indexed Tree 涉及 bit 運算, 需要對齊.
            tree = new int[m + 1][n + 1];
            nums = new int[m][n];
            // 遍歷每個格子
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    // 關鍵: 利用了 update 來構建, tree[i][j] 等於 不斷地在 i += i & (-i) 和  j += j & (-j) 路徑上, 累加 += "matrix[i][j] 的 value". 直到最後一格, 完成整個 tree 的構建.
                    update(i, j, matrix[i][j]);
        }

        public void update(int row, int col, int val) {
            if (m == 0 || n == 0) return;
            // 得出差值, 方便 tree 的 parent 更新, 因爲 value 都只是相對增加了 delta.
            int delta = val - nums[row][col];
            nums[row][col] = val;
            // 关键: 矩形中所有 row, col "相關的 父節點"(左到右/上到下) 都進行累加.
            for (int i = row + 1; i <= m; i += i & (-i))
                for (int j = col + 1; j <= n; j += j & (-j))
                    tree[i][j] += delta;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (m == 0 || n == 0) return 0;
            // 右下矩形 - 左边矩形 - 上边矩形 + 多减的左上重叠矩形
            return sum(row2 + 1, col2 + 1) - sum(row1, col2 + 1) - sum(row2 + 1, col1) + sum(row1, col1);
        }

        // 檢索 前n項的和 的過程, 求前 i 個的 sum, i -= lowbit(i), 累加這些結果, 直到 i == 0. 就是答案.
        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= i & (-i))
                for (int j = col; j > 0; j -= j & (-j))
                    sum += tree[i][j];
            return sum;
        }
    }
// time should be O(log(m) * log(n))


    /**
     * 線段樹解法, 有空看.
     */
    class NumMatrix2 {
        class SegmentTree2DNode {
            int startRow, startCol, endRow, endCol, sum;
            SegmentTree2DNode topLeft, topRight, bottomLeft, bottomRight;

            public SegmentTree2DNode(int startRow, int startCol, int endRow, int endCol) {
                this.startRow = startRow;
                this.startCol = startCol;
                this.endRow = endRow;
                this.endCol = endCol;
            }
        }

        private SegmentTree2DNode root;

        public NumMatrix2(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            root = buildTree(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
        }

        private SegmentTree2DNode buildTree(int[][] matrix, int startRow, int startCol, int endRow, int endCol) {
            if (startRow > endRow || startCol > endCol) return null;
            SegmentTree2DNode node = new SegmentTree2DNode(startRow, startCol, endRow, endCol);
            if (startRow == endRow && startCol == endCol) {
                node.sum = matrix[startRow][startCol];
            } else {
                int midRow = startRow + (endRow - startRow) / 2;
                int midCol = startCol + (endCol - startCol) / 2;
                node.topLeft = buildTree(matrix, startRow, startCol, midRow, midCol);
                node.topRight = buildTree(matrix, startRow, midCol + 1, midRow, endCol);
                node.bottomLeft = buildTree(matrix, midRow + 1, startCol, endRow, midCol);
                node.bottomRight = buildTree(matrix, midRow + 1, midCol + 1, endRow, endCol);
                node.sum = sum(node.topLeft) + sum(node.topRight) + sum(node.bottomLeft) + sum(node.bottomRight);
            }
            return node;
        }

        private int sum(SegmentTree2DNode node) {
            return node == null ? 0 : node.sum;
        }

        public void update(int row, int col, int val) {
            update(root, row, col, val);
        }

        private void update(SegmentTree2DNode node, int row, int col, int val) {
            if (node.startRow == node.endRow && node.startCol == node.endCol) {
                node.sum = val;
            } else {
                int midRow = node.startRow + (node.endRow - node.startRow) / 2;
                int midCol = node.startCol + (node.endCol - node.startCol) / 2;
                if (row <= midRow) {
                    if (col <= midCol) {
                        update(node.topLeft, row, col, val);
                    } else {
                        update(node.topRight, row, col, val);
                    }
                } else {
                    if (col <= midCol) {
                        update(node.bottomLeft, row, col, val);
                    } else {
                        update(node.bottomRight, row, col, val);
                    }
                }
                node.sum = sum(node.topLeft) + sum(node.topRight) + sum(node.bottomLeft) + sum(node.bottomRight);
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumRegion(root, row1, col1, row2, col2);
        }

        private int sumRegion(SegmentTree2DNode node, int startRow, int startCol, int endRow, int endCol) {
            if (node == null || startRow > node.endRow || endRow < node.startRow || startCol > node.endCol || endCol < node.startCol) {
                return 0;
            }
            if (startRow <= node.startRow && endRow >= node.endRow && startCol <= node.startCol && endCol >= node.endCol) {
                return node.sum;
            }
            return sumRegion(node.topLeft, startRow, startCol, endRow, endCol)
                    + sumRegion(node.topRight, startRow, startCol, endRow, endCol)
                    + sumRegion(node.bottomLeft, startRow, startCol, endRow, endCol)
                    + sumRegion(node.bottomRight, startRow, startCol, endRow, endCol);
        }
    }
}
