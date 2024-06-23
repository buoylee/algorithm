package cn.buoy.leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=KKxZaYyr44A
 * 簡單, 直接看視頻在看代碼, 注意細節處理.
 * 思路: 從 0 開始, bfs 4個方向, 當從不同的0(起點)出發到達 某個點的 step 不同時, 取最小的(但其實不用處理, 這裏是關鍵: 看註釋).
 */
public class Q286 {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        //BFS
        Queue<int[]> queue = new LinkedList<>();
        // 關鍵: 這裏 一次過 將所有的 0 都加入 queue, 使得在 bfs 時, 格子的value值, 只會到達這個格子的 value 相等或遞增(bfs step一致).
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                //将目的推入队列
                if (rooms[i][j] == 0) queue.add(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            //弹出队列中元素(坐标),尝试走出上下左右第一步, 有则入队
            int[] top = queue.remove();
            int row = top[0], col = top[1];
            // 因爲 bfs 遍歷元素時, step 一致, 導致不需要比大小更新value, 后走的value只会等于或大于当前值.
            //左
            // 關鍵: 因爲上邊所說 bfs 的原因, 使得, 只要是沒有檢查過的格子(Integer.MAX_VALUE)才需要更新值.
            if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row - 1, col});
            }
            //右
            if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            //上
            if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            //下
            if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
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
