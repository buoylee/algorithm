package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Stack;

public class Q230 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=n-PU677retk
     * 典型 stack inorder
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            //压到最左
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // 先node(A)本身, 再 右child tree(同理最左), 右child tree 結束後, 再彈出 下一個 node(node(A)的父節點)
            current = stack.pop();
            k--;
            if (k == 0)
                return current.val;
            current = current.right;
        }
        return -1; // This line will never be reached if k is valid
    }

    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        int count = 0;

        while (!stack.isEmpty() || curr != null) {
            //压到最左
            if (curr != null) {
                stack.push(curr);    // Just like recursion
                curr = curr.left;
            } else {
                // 先node(A)本身, 再 右child tree(同理最左), 右child tree 結束後, 再彈出 下一個 node(node(A)的父節點)
                TreeNode node = stack.pop();
                if (++count == k)
                    return node.val;
                curr = node.right;
            }
        }

        return Integer.MIN_VALUE;
    }

    /**
     * 递归
     */
    private static int number = 0;
    private static int count = 0;

    public int kthSmallest3(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }
}
