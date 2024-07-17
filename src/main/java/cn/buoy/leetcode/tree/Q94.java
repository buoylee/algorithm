package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Q94 {
    /**
     * 簡單, 2叉樹 inorder 遞歸寫法, 視頻有 stack 寫法.
     * https://www.youtube.com/watch?v=yIXvDlk2YrA
     */
    List<Integer> res;

    public List<Integer> inorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        helper(root);
        return res;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        res.add(root.val);
        helper(root.right);
    }

    /**
     * 2叉樹 inorder 遞歸寫法2
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        if (root == null) return pre;
        pre.addAll(inorderTraversal2(root.left));
        pre.add(root.val);
        pre.addAll(inorderTraversal2(root.right));
        return pre;
    }

    /**
     * stack 循環寫法
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.val);

            curr = curr.right;
        }

        return res;
    }
}
