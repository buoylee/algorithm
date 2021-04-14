package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q250 {

    /**
     * https://www.youtube.com/watch?v=UTf9Hn8CxLI&ab_channel=JacobHuang
     * <p>
     * 原理:由下往上进行判断是否为 uni-value subtree
     *
     * @param root
     * @return
     */
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1];
        helper(root, count);
        return count[0];
    }

    private boolean helper(TreeNode node, int[] count) {
        //叶子节点 单独成 子树
        if (node == null) {
            return true;
        }
        boolean left = helper(node.left, count);
        boolean right = helper(node.right, count);
        //左右子树, 只有 在都为 uni-value subtree 时, 该父节点才可能是 uni-value subtree.
        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }
}
