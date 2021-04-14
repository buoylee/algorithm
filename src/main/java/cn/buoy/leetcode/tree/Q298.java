package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q298 {
    private int max = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        helper(root, 0, root.val);
        return max;
    }

    public void helper(TreeNode root, int cur, int target) {
        //到底回头
        if (root == null) return;
        //如果 本值 与 父节点 + 1 相等, 说明是相连, ++
        if (root.val == target) cur++;
        //没到底, 又不连续, 则 回1重新统计.
        else cur = 1;
        max = Math.max(cur, max);
        helper(root.left, cur, root.val + 1);
        helper(root.right, cur, root.val + 1);
    }
}
