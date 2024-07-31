package cn.buoy.leetcode.backtracking;

public class Q351 {
    /**
     * 简单, 视频, 看代码
     * 题目: https://leetcode.com/problems/android-unlock-patterns/
     * https://www.youtube.com/watch?v=a6HrTxXUgvQ
     * https://www.youtube.com/watch?v=XrtayrXIWFE
     * 思路: backtracking, 分3种不同的起点, 角/边中点/中心点'5', 角/边中点 都有另外3种方向对称点; 中心点'5' 没有对称点.
     * 然后 dfs 种记录 "当前走了几步"(currentStep) 和 "当前位置"(currentNum), 不断遍历从 当前点到 所有没 visited 的点(如果中间存在 "隔着的点", 需要先 visited 过这个 "隔着的点"),
     * 如果 leastSteps <= 步数(currentStep) <=maxSteps, 则 count++
     */
    public int numberOfPatterns(int leastSteps, int maxSteps) {
        // jump[i][j] 指的是 从 i 到 j, 必须要先到达过 x 点. 例: 从 1 到 3 必先到 2.
        // 默认 == 0, 就是不需要jump. 注意: 例如 3->8 这种, 也算是直接到达, 不需要 jump 的格子.
        // 为了方便取值 从 i 到 j, 使用 len = 9 + 1 == 10
        int[][] jumps = new int[10][10];
        // 所有横竖
        jumps[1][3] = jumps[3][1] = 2;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5; // 对角线
        // 为了方便取值 从 i 到 j, 使用 len = 9 + 1 == 10
        boolean[] visited = new boolean[10];
        int result = 0;
        result += dfs(jumps, visited, 1, 1, 0, leastSteps, maxSteps) * 4; // 1, 3, 7, 9 are symmetrical
        result += dfs(jumps, visited, 2, 1, 0, leastSteps, maxSteps) * 4; // 2, 4, 6, 8 are symmetrical
        result += dfs(jumps, visited, 5, 1, 0, leastSteps, maxSteps);
        return result;
    }

    /**
     * @param currentNum  当前正在处理的数字格子
     * @param currentStep 当前路径(包括当前格子)一共走了几步
     * @param result      处理到现在, 一共有几种满足条件的走发.
     * @param leastSteps  答案要求最小步数
     * @param maxSteps    答案要求最大步数
     * @return
     */
    private int dfs(int[][] jumps, boolean[] visited, int currentNum, int currentStep, int result, int leastSteps, int maxSteps) {
        // 只统计 leastSteps <= 步数 <=maxSteps 的走法.
        if (currentStep >= leastSteps) result++; // only count if moves are larger than leastSteps
        currentStep++;
        if (currentStep > maxSteps) return result;
        visited[currentNum] = true;
        // 当前点 向任意一格迈出
        for (int next = 1; next <= 9; next++) {
            // 关键: 只有是下一步没有访问过, 且 (不用穿越`其他格子`(jump == 0), 或者 `穿越的格子` 已经访问过(visited[jump] == true)), 才是满足条件的下一步.
            int jump = jumps[currentNum][next];
            if (!visited[next] && (jump == 0 || visited[jump]))
                result = dfs(jumps, visited, next, currentStep, result, leastSteps, maxSteps);
        }
        visited[currentNum] = false; // backtracking
        return result;
    }
}
