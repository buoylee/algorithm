package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Stack;

public class Q98 {
    /**
     * 中序遍历 迭代
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            //一直往左找, 找到null, 回头判断
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //第一次是最左node
            //第2次是parent
            root = stack.pop();
            //parent 比左小, false
            if (pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    /**
     * https://www.youtube.com/watch?v=DYNiCaKHneY
     * 递归
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}
