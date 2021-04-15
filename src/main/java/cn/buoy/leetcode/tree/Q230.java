package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Stack;

public class Q230 {

    /**
     * 就是中序遍历
     * 迭代
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        int count = 0;

        while (!stack.isEmpty() || p != null) {
            //压到最左
            if (p != null) {
                stack.push(p);    // Just like recursion
                p = p.left;
            } else {
                //弹出, 先node本身, 再右node.
                //重复压左到结束.
                TreeNode node = stack.pop();
                if (++count == k) return node.val;
                p = node.right;
            }
        }

        return Integer.MIN_VALUE;
    }

    /**
     * 递归
     */
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest2(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }
}
