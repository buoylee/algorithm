package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q104 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=fEsr9cYrZZU
     * 思路: dfs 遞歸, 不爲 null 就 + 1
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
