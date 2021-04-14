package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Stack;

public class Q173 {
    /**
     * https://www.youtube.com/watch?v=7p8Nr_-lMZM
     */
    public class BSTIterator {
        private Stack<TreeNode> stack = new Stack<TreeNode>();

        public BSTIterator(TreeNode root) {
            pushAll(root);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode tmpNode = stack.pop();
            pushAll(tmpNode.right);
            return tmpNode.val;
        }

        private void pushAll(TreeNode node) {
            for (; node != null; stack.push(node), node = node.left) ;
        }
    }
}
