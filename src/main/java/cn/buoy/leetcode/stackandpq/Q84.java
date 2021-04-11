package cn.buoy.leetcode.stackandpq;

import java.util.LinkedList;
import java.util.Stack;

public class Q84 {
    public static void main(String[] args) {
        int[] ints = {3, 3, 3, 1};
        Q84 q84 = new Q84();
        int i = q84.largestRectangleArea(ints);
    }

    /**
     * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28902/5ms-O(n)-Java-solution-explained-(beats-96)
     */
    // TODO: 2021/4/12

    /**
     * https://www.youtube.com/watch?v=XwUb7x6YDeA
     */
    public int largestRectangleArea(int[] a) {
        LinkedList<Integer> stack = new LinkedList<>();
//        Stack<Integer> stack = new Stack<>();

        int max=0;

        for (int i = 0; i <= a.length; i++) {
            // if we finished all elements OR the current element is less than top
            while (!stack.isEmpty() && (i==a.length || a[stack.peek()]>a[i]) ){
                int height = a[stack.pop()];
                int width = (!stack.isEmpty()) ? i-stack.peek()-1 : i;
                max = Math.max(max, height * width);
            }

            stack.push(i);
        }

        return max;
    }



    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            //如果stack为空 或 当前h比栈顶的大于等于 的话, 入栈.
            if (s.isEmpty() || h >= heights[s.peek()]) {
                s.push(i);
            } else {
                //当前h = [i] 小于 栈顶 时, 站在 前一位(i - 1)的高度往以前看, 直到延伸到栈顶为小于 i 的高度 的位置(j)为止. 从而确定了 j 到 i-1 位置高度的所有面积.
                int tp = s.pop();
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }


    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }
}
