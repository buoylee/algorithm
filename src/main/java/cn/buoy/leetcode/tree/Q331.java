package cn.buoy.leetcode.tree;

import java.util.Stack;

public class Q331 {
    /**
     * 懂了就簡單, 出入度, 視頻
     * https://www.youtube.com/watch?v=_mbnPPHJmTQ&t=94s
     * 思路: 初始(沒有 root) 爲 1 度;
     * 出現 root, 度 -1; curr != null, 度 + 2;
     * 檢查 root.left, 度 -1; curr != null, 度 + 2;
     * 檢查 root.left.left, 度 -1; curr == null, 度 + 0;
     * 檢查 root.left.right, 度 -1; curr == null, 度 + 0;
     * ...
     * 最後剛好 度 == 0, 就是 ValidSerialization.
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node : nodes) {
            // 每遍歷一個 node, 先 "度--"; 減完, 度 < 0 就不合法.
            if (--diff < 0) return false;
            // 只有 node != null, "度+2"
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }

    /**
     * stack法 的步驟, 和解析 都在下邊, 完了就看.
     * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/78566/Java-intuitive-22ms-solution-with-stack
     * // using a stack, scan left to right
     * // case 1: we see a number, just push it to the stack
     * // case 2: we see #, check if the top of stack is also #
     * // if so, pop #, pop the number in a while loop, until top of stack is not #
     * // if not, push it to stack
     * // in the end, check if stack size is 1, and stack top is #
     */
    public boolean isValidSerialization2(String preorder) {
        if (preorder == null) return false;
        Stack<String> stack = new Stack<>();
        String[] strs = preorder.split(",");
        for (int i = 0; i < strs.length; i++) {
            String curr = strs[i];
            //如果栈顶还是#, 则需要弹2次(回到叶子节点的父节点 或 左节点), 如果谈一次就空了 ,不能构成一个前序遍历. 直到栈顶不是#才停下.
            while (curr.equals("#") && !stack.isEmpty() && stack.peek().equals(curr)) {
                stack.pop();
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
            //连续 pop 2次 # 后, push 后来的 #, 其实是将这个子树置为null来看待. 因爲 null 表示, 這個 node 的 subtree 已經 填完了(都到達了葉子節點).
            /**
             * https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
             */
            // 記得把 curr push stack
            stack.push(curr);
        }
        //最后 剩一个 #, 才是合理的 "前序 list str".
        return stack.size() == 1 && stack.peek().equals("#");
    }
}
