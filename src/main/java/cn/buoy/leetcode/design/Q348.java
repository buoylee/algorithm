package cn.buoy.leetcode.design;

public class Q348 {
    /**
     * 簡單, 直到思路的話, 視頻
     * https://www.youtube.com/watch?v=Ol9Fp4c_VP4
     * 思路: 用 +/-1 記錄 正反方在所有 行/列/對角線的 下棋次數, 只有其中出現 +/-n(正方形邊長), 說明 有player走贏了.
     */
    public class TicTacToe {
        private int[] rows; // 所有行
        private int[] cols; // 所有列
        private int diagonal; // 對角線
        private int antiDiagonal; // 反對角線
        private int n; //正方形邊長

        public TicTacToe(int n) {
            this.n = n;
            rows = new int[n];
            cols = new int[n];
            diagonal = 0;
            antiDiagonal = 0;
        }

        public int move(int row, int col, int player) {
            int toAdd = player == 1 ? 1 : -1;
            rows[row] += toAdd;
            cols[col] += toAdd;
            if (row == col)
                diagonal += toAdd;
            if (row + col == n - 1)
                antiDiagonal += toAdd;
            // 走完一步, 檢查一次, 是否有 出現 +/-n
            if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n)
                return player;
            return 0;
        }
    }

    public class TicTacToe2 {
        //这些是指 某行某列 有几个1 或 -1, 题目保证不会重复点.
        private int[] rows;
        private int[] cols;
        //对角线就2条, 正, 反
        private int diagonal;
        private int antiDiagonal;

        /**
         * Initialize your data structure here.
         */
        public TicTacToe2(int n) {
            rows = new int[n];
            cols = new int[n];
        }

        /**
         * Player {player} makes a move at ({row}, {col}).
         *
         * @param row    The row of the board.
         * @param col    The column of the board.
         * @param player The player, can be either 1 or 2.
         * @return The current winning condition, can be either:
         * 0: No one wins.
         * 1: Player 1 wins.
         * 2: Player 2 wins.
         */
        public int move(int row, int col, int player) {
            //正反方 放 1, -1
            int toAdd = player == 1 ? 1 : -1;

            rows[row] += toAdd;
            cols[col] += toAdd;
            //只有2条对角线 能满足n的条件. 所以只用看 row == col 和 col == (cols.length - row - 1) 的情况
            if (row == col)
                diagonal += toAdd;
            if (col + row == cols.length - 1)
                antiDiagonal += toAdd;
            //最后, 只要 任一 达到n个, 就返回 当前player,
            int size = rows.length;
            if (Math.abs(rows[row]) == size ||
                    Math.abs(cols[col]) == size ||
                    Math.abs(diagonal) == size ||
                    Math.abs(antiDiagonal) == size) {
                //1或2
                return player;
            }
            // 没有人赢
            return 0;
        }
    }
}
