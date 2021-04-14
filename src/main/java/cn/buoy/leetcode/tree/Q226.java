package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Q226 {
    /**
     * 迭代
     * stack 深度优先
     * 换queue 就是广度
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {

        if (root == null) {
            return null;
        }

        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * 递归, 简单
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        final TreeNode left = root.left,
                right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
}
