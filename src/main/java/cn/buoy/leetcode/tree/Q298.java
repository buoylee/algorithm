package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q298 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=jF7X1DFUHnI
     * 思路: dfs, 一路驗證 "連續數" 到 node == null, 更新 max.
     */
    private int max = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0, root.val);
        return max;
    }

    public void dfs(TreeNode root, int actualNum, int target) {
        // 到葉子節點
        if (root == null) return;
        //如果 實際值 == target 相等, 就是相连, 然後 actualNum++ 提供給 "下一層dfs"
        if (root.val == target)
            actualNum++;
        else // 没到底, 又不连续, 成爲起點.
            actualNum = 1;
        max = Math.max(actualNum, max);
        dfs(root.left, actualNum, root.val + 1);
        dfs(root.right, actualNum, root.val + 1);
    }
}
