package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q112 {
    /**
     * 簡單, 視頻.
     * https://www.youtube.com/watch?v=C0pOWEucp4M
     * 思路: 遞歸, 到達葉子 node 時, 剛好 == sum
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        // 如果 剩下的 与 当前root相等则 答案为true
        if (root.left == null && root.right == null && sum - root.val == 0) return true;
        // 减去当前值后, 继续往下.
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
