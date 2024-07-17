package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Stack;

public class Q101 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=ULk7EuK0n9Q
     * 思路: 畫圖找規律, 發現剛好可以 isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left)
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        // 不夠直觀
//        if (left == null || right == null)
//            return left == right;
//        if (left.val != right.val)
//            return false;
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    // 只是用 stack 做, 沒上邊直觀
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.empty()) {
            //都是一对对 对比,
            TreeNode n1 = stack.pop(), n2 = stack.pop();
            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null || n1.val != n2.val) return false;
            //
            stack.push(n1.left);
            stack.push(n2.right);
            //
            stack.push(n1.right);
            stack.push(n2.left);
        }
        return true;
    }
}
