package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q124 {

    /**
     * https://www.youtube.com/watch?v=VgbU4D6WuGs&ab_channel=happygirlzt
     * 题目好奇怪
     */
    int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    /**
     * 其实就是找一个 左右节点(各个子树), 一个 路径 的最大值.
     *
     * @param node
     * @return
     */
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
