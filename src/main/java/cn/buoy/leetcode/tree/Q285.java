package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q285 {
    /**
     * 簡單, 視頻講的不清楚, 画图理解
     * https://www.youtube.com/watch?v=8eRA2KW3yus
     * 思路: 如果 curr > target, 所以, curr 有可能是 successor, 更新 successor, 然後往左走, 看看有沒 target < node < 當前 successor.
     * 如果 curr <= target, 說明 successor 在 curr 的右邊("right child" 或者 "在之前出現過"(之前的 successor));
     * 如此循環到 curr == null, 最後返回 successor.
     */
    public TreeNode successor(TreeNode curr, TreeNode target) {
        TreeNode result = null;
        while (curr != null) {
            // 因爲 curr > target, 所以, curr 有可能是 successor, 更新 successor,
            // 然後往左走, 看看有沒 target < node < "當前 successor"
            if (curr.val > target.val) {
                result = curr;
                curr = curr.left;
            } else
                curr = curr.right;
        }
        return result;
    }

    // 上邊比較直觀
    public TreeNode successor2(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        if (root.val <= p.val)
            return successor(root.right, p);
        //如果找不到, 这里是最后一个还向左的root,这个点就是后续node.
        //要画图
        TreeNode left = successor(root.left, p);
        //如果left 不等于 空, 就是有解,
        return (left != null) ? left : root;
    }
}
