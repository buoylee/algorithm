package cn.buoy.leetcode.dp;

import java.util.Stack;

public class Q85 {
    /**
     * https://www.youtube.com/watch?v=2Yk3Avrzauk
     * 只要懂了 84题, 剩下就是转化成对应的柱形图表示, 然后调用84 func 即可.
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] heights = new int[n]; // using a array to reduce counting step of 1
        int max = 0;
        for (char[] row : matrix) {
            for (int i = 0; i < n; i++) {
                //遇1, 则heights[i]++
                if (row[i] == '1') {
                    heights[i] += 1;
                } else {
                    //遇0, 则 置0
                    heights[i] = 0;
                }
            }
            //然后call func 84 即可
            max = Math.max(max, maxArea(heights)); // go a sub problem of Histogram
        }
        return max;
    }

    // Exact same solution to Histogram
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
