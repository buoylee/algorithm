package cn.buoy.leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

public class Q286 {
    /**
     * 簡單, 直接看視頻在看代碼, 注意細節處理.
     * https://www.youtube.com/watch?v=KKxZaYyr44A
     * 思路: bfs. 把所有 '0' 開始 都先入 queue, bfs 4個方向, 就是答案.
     * 當從 不同的 '0'(起點) 出發到達 某個點的 step 不同時, 并不会影响 "最小 步长", 因为 所有的 '0' 是 同步进行(同一层) bfs, 所以 后经过的路径长度只会相同或更长.
     */
    public void wallsAndGates(int[][] rooms) {
        int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if (rooms.length == 0 || rooms[0].length == 0) return;
        Queue<int[]> queue = new LinkedList<>();
        // 關鍵: 這裏一次過, 將矩阵中所有的 '0' 都加入 queue, 使得在 bfs 時, 格子的value值, 只會到達這個格子的 value 相等或遞增(bfs step一致).
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0) queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] node = queue.remove();
            int row = node[0], col = node[1];
            // 上
            // 因爲上邊所說 bfs 的原因, 使得, 只要是沒有檢查過的格子(Integer.MAX_VALUE)才需要更新值.
            if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row - 1, col});
            }
            // 下
            if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            // 左
            if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            // 右
            if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
            // 上下左右 // todo 未测试
//            for (int i = 0; i < d.length; i++) {
//                int newRow = row + d[i][0];
//                int newCol = row + d[i][1];
//                if (0 <= newRow && newRow < rooms.length && 0 <= newCol && newCol < rooms[0].length && rooms[newRow][newCol] == Integer.MAX_VALUE) {
//                    rooms[newRow][newCol] = rooms[row][col] + 1;
//                    queue.add(new int[]{newRow, newCol});
//                }
//            }
        }
    }


    // 擴展: DFS, 有空再看.
    private static int[] d = {0, 1, 0, -1, 0};

    public void wallsAndGates2(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[0].length; j++)
                if (rooms[i][j] == 0) dfs(rooms, i, j);
    }

    public void dfs(int[][] rooms, int i, int j) {
        for (int k = 0; k < 4; ++k) {
            //拼凑上下左右移动
            int p = i + d[k], q = j + d[k + 1];
            //坐标在合理范围内, 则判断遍历到坐标的值, 如果小则更新.
            if (0 <= p && p < rooms.length && 0 <= q && q < rooms[0].length && rooms[p][q] > rooms[i][j] + 1) {
                rooms[p][q] = rooms[i][j] + 1;
                dfs(rooms, p, q);
            }
        }
    }
}
