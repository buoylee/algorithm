package cn.buoy.leetcode.stackandpq;

import java.util.LinkedList;
import java.util.Stack;

public class Q84 {
    /**
     * 視頻講的很好, 由暴力解法(每個柱往2邊延伸(要高於自己的高度), O(n^2))發想過來.
     * https://www.youtube.com/watch?v=XwUb7x6YDeA
     * 思路: 如果數組遞增時, 入stack, 每當發現'當前高度'低於'前一個', 即找到了'前一個'的延伸範圍, 就可以得到 '前一個' 高度下的 '最大正方形'.
     * 細節: 下邊的寫法會出現, 相同的高度連續入stack, 當pop時, 會計算多次同一高度, 但是無所謂, 越後pop的同高度的面積越大.
     */
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int max = 0;
        // 關鍵: 當到達 heights.length(heights長度+1), 由於最後 heights 可能沒有遞減, 所以需要在遍歷完成後, 往前pop出所有stack ele.
        for (int i = 0; i <= heights.length; i++) {
            // if we finished all elements OR the current element is less than top
            while (!stack.isEmpty() && (i == heights.length || heights[stack.peek()] > heights[i])) {
                int height = heights[stack.pop()];
                // 因爲不包含當前高度柱子, 需要多 -1.
                int width = (!stack.isEmpty()) ? i - stack.peek() - 1 : i;
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

    /**
     * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28902/5ms-O(n)-Java-solution-explained-(beats-96)
     */
    // TODO: 2021/4/12
    public static void main(String[] args) {
        int[] ints = {3, 3, 3, 1};
        Q84 q84 = new Q84();
        int i = q84.largestRectangleArea(ints);
    }
}
