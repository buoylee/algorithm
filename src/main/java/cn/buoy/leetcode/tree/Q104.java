package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q104 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
