package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

/**
 * 简单
 */
public class Q100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }
}
