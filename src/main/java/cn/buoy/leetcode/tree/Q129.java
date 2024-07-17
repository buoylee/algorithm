package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q129 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=6fC6O5lkSkg
     * 思路: 到達 葉子節點, 才把 "組成完整的數" 累加到 result 中.
     */
    int res = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return res;
        helper(root, 0);
        return res;
    }

    private void helper(TreeNode node, int preDigits) {
        if (node == null) return;
        // 到達叶子节点
        if (node.left == null && node.right == null) {
            res += preDigits * 10 + node.val;
            return;
        }
        // 任意 組合
        helper(node.left, preDigits * 10 + node.val);
        helper(node.right, preDigits * 10 + node.val);
    }

    // 上邊的代碼比較直觀
    public int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode node, int num) {
        if (node == null) return 0;
        // 到達叶子节点
        int curr = num * 10 + node.val;
        // 每一個 葉子節點 都會 返回 完成組合的數.
        if (node.right == null && node.left == null)
            return curr;
        // 這裏不好理解, 任意 左/右葉子 節點 之和.
        return dfs(node.left, curr) + dfs(node.right, curr);
    }
}
