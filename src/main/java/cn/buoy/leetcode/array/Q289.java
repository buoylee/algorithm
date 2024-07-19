package cn.buoy.leetcode.array;

public class Q289 {
    /**
     * 簡單, 懂得利用bit來同時保存 下次/當前死活狀態的話.
     * https://www.youtube.com/watch?v=9AsUixzUGa0
     * https://www.youtube.com/watch?v=HKPUXZW-ACo 短
     * 關鍵: 用 2個 bit 表示 下次/當前死活狀態(例如: 11(也就是3), 下次活/當前活). 因爲 每個格子死活, 都需要 原數據 來做判斷. 所以如果前邊的格子修改了狀態, 後邊格子的無法操作了.
     * 因此, 我們只要能 "同時保存 下次/當前死活狀態" 的話, 就可以統一用先用當前狀態處理完所有格子後, 再把下次的狀態呈現出來就好.
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int rowLen = board.length, colLen = board[0].length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                // 获取 周围8格 live的数量
                int lives = liveNeighbors(board, rowLen, colLen, i, j);
                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                // 關鍵: 这里是只要 關注 改變第2个bit状态 就好. 因爲 第2位本來就是 0. 所以 "下次死/當前活"(lives < 2 || lives > 3) 和 "下次死/當前死"(curr == 0 && lives != 3) 都不用處理.
                // 不用看. 屬於第一個視頻相關. 和视频不一样, 视频是直接处理定义好的表示从`死到活`, `活到死`的2个新state,完成状态变化后, 在恢复2个新状态到0, 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3)
                    // 表示持续存活
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                if (board[i][j] == 0 && lives == 3)
                    // 表示由死变活
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
            }
        }
        //最后把 格子的 第2位 移回 第1位.
        for (int i = 0; i < rowLen; i++)
            for (int j = 0; j < colLen; j++)
                board[i][j] >>= 1;  // Get the 2nd state.
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
