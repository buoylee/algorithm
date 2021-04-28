package cn.buoy.leetcode.backtracking;

public class Q351 {
    /**
     * 题目: https://leetcode.com/problems/android-unlock-patterns/
     * https://www.youtube.com/watch?v=XrtayrXIWFE
     */
    private int[][] jumps;
    private boolean[] visited;

    public int numberOfPatterns(int m, int n) {
        //jump 指的是 从前点到后点, 必须要经过的x点.
        jumps = new int[10][10];
        jumps[1][3] = jumps[3][1] = 2;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5;
        visited = new boolean[10];
        int count = 0;
        count += DFS(1, 1, 0, m, n) * 4; // 1, 3, 7, 9 are symmetrical
        count += DFS(2, 1, 0, m, n) * 4; // 2, 4, 6, 8 are symmetrical
        count += DFS(5, 1, 0, m, n);
        return count;
    }

    /**
     * @param num   当前正在处理的 上步到该步判断合理的格子
     * @param len   当前路径(包括当前格子)一共走了几步
     * @param count 处理到现在, 一共有几种满足条件的走发.
     * @param m     答案要求最小步数
     * @param n     答案要求最大步数
     * @return
     */
    private int DFS(int num, int len, int count, int m, int n) {
        //只统计步数 >=m, <=n 的走法.
        if (len >= m) count++; // only count if moves are larger than m
        len++;
        if (len > n) return count;

        visited[num] = true;
        //当前点想任意一格迈出
        for (int next = 1; next <= 9; next++) {
            int jump = jumps[num][next];
            //重点: 判断合理的下一步.
            //只有时下一步没有访问过, 且 (不用穿越`其他格子`, 或者`穿越的格子`已经访问过), 才是满足条件的下一步.
            if (!visited[next] && (jump == 0 || visited[jump])) {
                count = DFS(next, len, count, m, n);
            }
        }
        visited[num] = false; // backtracking
        return count;
    }
}
