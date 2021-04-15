package cn.buoy.leetcode.tree;

import java.util.Stack;

public class Q255 {
    /**
     * https://www.youtube.com/watch?v=Psce8aMuX8s
     * 一定要画图!
     * 原理: 如果遇到递增数时, 最后排出的小于递增数的 值, 作为最小值, 往后的值 都不能够小于这个最小值.
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack();
        for (int p : preorder) {
            //如果 当前 node 小于 最低值, 返回false.
            if (p < low)
                return false;
            //如果 stack 有值, 且 前序[]的 当前 node 大于栈顶, 则 弹出并设置为最低值.
            while (!path.empty() && p > path.peek())
                low = path.pop();
            path.push(p);
        }
        return true;
    }
}
