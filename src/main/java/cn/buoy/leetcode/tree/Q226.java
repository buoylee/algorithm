package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Q226 {
    /**
     * 简单, 視頻
     * https://www.youtube.com/watch?v=RhWupnW_-kw
     * 思路: 递归翻轉.
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 關鍵: 將 完成遞歸翻轉下層的 left/right 放入 root 相反的位置.
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    /**
     * 迭代
     * stack 深度优先
     * 换queue 就是广度
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


}
