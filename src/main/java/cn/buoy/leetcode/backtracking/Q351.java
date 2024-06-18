package cn.buoy.leetcode.backtracking;

public class Q351 {
    /**
     * 题目: https://leetcode.com/problems/android-unlock-patterns/ https://www.youtube.com/watch?v=a6HrTxXUgvQ
     * <p>
     * https://www.youtube.com/watch?v=XrtayrXIWFE
     * 看视频思路, 然后看代码
     */
    private int[][] jumps;
    private boolean[] visited;

    public int numberOfPatterns(int leastSteps, int maxSteps) {
        //jump 指的是 从前点到后点, 必须要经过的x点.
        // 默认 == 0, 就是不需要jump. 注意: 例如 3->8这种, 也算是直接到达, 不需要skip.
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
        count += DFS(1, 1, 0, leastSteps, maxSteps) * 4; // 1, 3, 7, 9 are symmetrical
        count += DFS(2, 1, 0, leastSteps, maxSteps) * 4; // 2, 4, 6, 8 are symmetrical
        count += DFS(5, 1, 0, leastSteps, maxSteps);
        return count;
    }

    /**
     * @param currentNum  当前正在处理的数字格子
     * @param currentStep 当前路径(包括当前格子)一共走了几步
     * @param result      处理到现在, 一共有几种满足条件的走发.
     * @param leastSteps  答案要求最小步数
     * @param maxSteps    答案要求最大步数
     * @return
     */
    private int DFS(int currentNum, int currentStep, int result, int leastSteps, int maxSteps) {
        //只统计步数 >=leastSteps, <=maxSteps 的走法.
        if (currentStep >= leastSteps) result++; // only count if moves are larger than leastSteps
        currentStep++;
        if (currentStep > maxSteps) return result;

        visited[currentNum] = true;
        //当前点 向任意一格迈出
        for (int next = 1; next <= 9; next++) {
            //重点: 只有是下一步没有访问过, 且 (不用穿越`其他格子`(jump == 0), 或者`穿越的格子`已经访问过(visited[jump] == true)), 才是满足条件的下一步.
            int jump = jumps[currentNum][next];
            if (!visited[next] && (jump == 0 || visited[jump])) {
                result = DFS(next, currentStep, result, leastSteps, maxSteps);
            }
        }
        visited[currentNum] = false; // backtracking
        return result;
    }
}
