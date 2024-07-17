package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q156 {
    /**
     * 簡單, 知道做法的話. 應該不會考.
     * https://leetcode.com/problems/binary-tree-upside-down/discuss/49406/Java-recursive-(O(logn)-space)-and-iterative-solutions-(O(1)-space)-with-explanation-and-figure
     * https://www.youtube.com/watch?v=6HYaPnjhzjg
     * https://www.youtube.com/watch?v=6ahTTJTwoDs&t=82s
     * 思路: 視頻, 規律
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode left = root.left;
        TreeNode newRoot = upsideDownBinaryTree(left);
        left.left = root.right;   // node 2 left children
        left.right = root;         // node 2 right children
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        TreeNode curr = root;
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode prev = null;

        while (curr != null) {
            next = curr.left;

            // swapping nodes now, need temp to keep the previous right child
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}
