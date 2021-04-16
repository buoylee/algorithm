package cn.buoy.leetcode.tree;

import java.util.Stack;

public class Q331 {
    /**
     * https://www.youtube.com/watch?v=_mbnPPHJmTQ&t=94s
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node : nodes) {
            if (--diff < 0) return false;
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }

    /**
     * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/78566/Java-intuitive-22ms-solution-with-stack
     */
    public boolean isValidSerialization2(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            //如果栈顶还是#, 则需要弹2次(回到叶子节点的父节点 或 左节点), 如果谈一次就空了 ,不能构成一个前序遍历. 直到栈顶不是#才停下.
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            //连续2出#,pop 2次后 push 后来的 #, 其实是将这个子树置为null来看待.
            /**
             * https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
             */
            st.push(curr);
        }
        //最后 剩一个元素, 且是 # 则true.
        return st.size() == 1 && st.peek().equals("#");
    }
}
