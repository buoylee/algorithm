package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Stack;

public class Q173 {
    /**
     * 簡單, inorder 的 stack 做法.
     * https://www.youtube.com/watch?v=7p8Nr_-lMZM
     * 思路: stack 實現 inorder, 先把所有 left node 都push, 然後 取 root, 取 right node, 再 push "right node" 的 "left node"...
     */
    public class BSTIterator {
        private Stack<TreeNode> stack = new Stack<TreeNode>();

        public BSTIterator(TreeNode root) {
            pushAllLeft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode tmpNode = stack.pop();
            pushAllLeft(tmpNode.right);
            return tmpNode.val;
        }

        private void pushAllLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}
