package cn.buoy.leetcode.design;

public class Q348 {
    /**
     * https://www.youtube.com/watch?v=Ol9Fp4c_VP4
     */
    public class TicTacToe {
        //这些是指 某行某列 有几个1 或 -1, 题目保证不会重复点.
        private int[] rows;
        private int[] cols;
        //对角线就2条, 正, 反
        private int diagonal;
        private int antiDiagonal;

        /**
         * Initialize your data structure here.
         */
        public TicTacToe(int n) {
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
            if (row == col) {
                diagonal += toAdd;
            }

            if (col == (cols.length - row - 1)) {
                antiDiagonal += toAdd;
            }

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
