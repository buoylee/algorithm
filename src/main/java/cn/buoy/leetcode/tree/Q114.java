package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q114 {
    /**
     * 簡單, 要看視頻就看下邊.
     * 思路: 典型 preorder 就能解.
     */
    public void flatten(TreeNode root) {
        ArrayList<TreeNode> preorderList = new ArrayList<>();
        preorder(preorderList, root);
        TreeNode dummy = new TreeNode(0);
        TreeNode curr = dummy;
        // 一直 斷掉 root 左 node, 然後 root.right = root.left
        for (TreeNode n : preorderList) {
            curr.left = null;
            curr.right = n;
            curr = curr.right;
        }
        // root 就是原來的 root, 什麼都不用做.
    }

    // 轉爲 preorder list
    public void preorder(List<TreeNode> list, TreeNode node) {
        if (node == null) return;
        list.add(node);
        preorder(list, node.left);
        preorder(list, node.right);
    }

    /**
     * 認真看代碼其實都能理解.
     * https://www.youtube.com/watch?v=v2ob-ek9TgE
     * 思路: 也是 每個 subtree root->left->right
     */
    public void flatten2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 彈出 root
            TreeNode curr = stack.pop();
            // 按 right 然後 left 的順序 stackpushing
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
            // left 成爲 root 的 right node. 先不用連接 left -> right, 後續處理.
            // 此時 root 已完成不需要關注, 繼續 處理 stack 的 root.left 和 root.right
            if (!stack.isEmpty())
                curr.right = stack.peek();
            //断开 root 左边node
            curr.left = null;  // dont forget this!!
        }
    }

    /**
     * 都一樣, 代碼實現不同而已. 真的都差不多.
     * postorder
     * 思路: 也是 每個 subtree root->left->right
     */
    public void flatten3(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;

        TreeNode current = root;
        //  找到 捋直了的 "root.left" 的最後一個 right
        while (current.right != null) {
            current = current.right;
        }
        // 此時, "current.right" 表示的是, 捋直了的 "root.left" 的最後一個 right,
        // 把 捋直的"root.right" 接在 "current.right" 後邊.
        current.right = right;
    }

    private TreeNode prev = null;

    public void flatten4(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
