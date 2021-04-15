package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Stack;

public class Q114 {
    /**
     * 重点: 思考方式, 最小化问题并解决.
     * https://www.youtube.com/watch?v=v2ob-ek9TgE
     * 拆分成最小3点(root, left, right)的方式去思考, 是每个最小3点, 都是 root -> left -> right
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode curr = stk.pop();
            if (curr.right != null)
                stk.push(curr.right);
            if (curr.left != null)
                stk.push(curr.left);
            //root.right 指向 root.left, 再一次, left.right 指向 root.right. 以此类推.
            if (!stk.isEmpty())
                curr.right = stk.peek();
            //断开 root 左边node
            curr.left = null;  // dont forget this!!
        }
    }


    private TreeNode prev = null;

    public void flatten2(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
