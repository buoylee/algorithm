package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q250 {
    /**
     * 簡單, 後續遍歷, 視頻
     * https://www.youtube.com/watch?v=UTf9Hn8CxLI
     * 原理: 後續遍歷, 下往上进行判断是否为 uni-value subtree
     */
    int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        helper(root);
        return count;
    }

    private boolean helper(TreeNode node) {
        // 任何 node 和 null 都能組成 UnivalTree
        if (node == null) return true;
        boolean left = helper(node.left);
        boolean right = helper(node.right);
        //左右子树, 都各自爲 uni-value subtree 时, 父节点才有可能是 uni-value subtree.
        if (left && right) {
            // 關鍵: 如果 left/right 是 null, 和任何 node 仍然組成 uni-value subtree,
            // 所以 一定要判斷 != null && child.val == parent.val
            if (node.left != null && node.val != node.left.val)
                return false;
            if (node.right != null && node.val != node.right.val)
                return false;
            count++;
            return true;
        }
        return false;
    }
}
