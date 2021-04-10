package cn.buoy.leetcode.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

public class Q286 {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        //BFS
        Queue<int[]> queue = new LinkedList<>();
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
            //如果有新坐标则value = 上一步value + 1(不需要比大小更新value, 后走的value只会等于或大于当前值)
            //左
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


    //DFS
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
