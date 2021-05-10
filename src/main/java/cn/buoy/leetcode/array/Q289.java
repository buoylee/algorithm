package cn.buoy.leetcode.array;

public class Q289 {
    /**
     * https://www.youtube.com/watch?v=9AsUixzUGa0
     * 关键思路, 因为每个都是同步update, 为了节省空间, 在原matrix上操作, 需要辨认出修改后的格子原state是死活, 所以整增加2状态表示状态的变化类型.
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //获取周围8格live数量
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                //这里是只要观察第2个bit状态就好.
                //和视频不一样, 视频是直接处理定义好的表示从`死到活`, `活到死`的2个新state,完成状态变化后, 在恢复2个新状态到0, 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    //表示持续存活
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    //表示由死变活
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        //最后把2位的格子变回一位即可
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        //Math.max(i - 1, 0) 上边界防越界
        //Math.min(i + 1, m - 1) 下边界防越界
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            //Math.max(j - 1, 0) 左边界防越界
            //Math.max(j + 1, n - 1) 右边界防越界
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                //如果是live的第一位是0, lives++
                lives += board[x][y] & 1;
            }
        }
        //如果当前点是live, 扣除掉自己.
        lives -= board[i][j] & 1;
        return lives;
    }
}
