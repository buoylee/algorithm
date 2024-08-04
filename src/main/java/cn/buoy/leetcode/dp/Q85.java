package cn.buoy.leetcode.dp;

import java.util.Stack;

public class Q85 {
    /**
     * 84. 只要懂了 84题, 剩下就是转化成对应的柱形图表示, 然后调用84 func 即可.
     * 视频
     * https://www.youtube.com/watch?v=2Yk3Avrzauk
     * 思路: 一行行来检查 柱型图, 增加一行就当作新的 柱形图,
     * 如果 "新行某列" 为 '0', 新柱形图 此列归零;
     * 如果 "新行某列" 为 '1', 新柱形图 "该列 height" + 1
     * 更新完 一行, 就可以作为 新的柱形图 call 84 func.
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int heightLen = matrix[0].length;
        int[] heights = new int[heightLen]; // using a array to reduce counting step of 1
        int result = 0;
        for (char[] row : matrix) {
            for (int i = 0; i < heightLen; i++) {
                // 遇1, 则heights[i]++
                if (row[i] == '1') {
                    heights[i] += 1;
                } else
                    // 遇0, 则置0
                    heights[i] = 0;
            }
            // 没处理完一行, 就 call func 84 即可.
            result = Math.max(result, maxArea(heights)); // go a sub problem of Histogram
        }
        return result;
    }

    /**
     * 這個函數 相同題目在 84.
     * Exact same solution to Histogram
     */
    public int maxArea(int[] heights) {
        Stack<Integer> stack = new Stack();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int index = stack.pop();
                int leftBound = -1;
                if (!stack.isEmpty()) {
                    leftBound = stack.peek();
                }
                max = Math.max(max, heights[index] * (i - leftBound - 1));
            }
            stack.push(i);
        }
        return max;
    }
}
