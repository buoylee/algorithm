package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q111 {
    public int minDepth(TreeNode root) {
        //null 即 0 层
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //选最少的往上传
        //关键在这, 只有是叶子节点(左右节点都为nul)的层数才算.
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;

    }
}
