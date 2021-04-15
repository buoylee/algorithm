package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q99 {
    /**
     * https://www.youtube.com/watch?v=sDQhHZJkNh0
     * 简单, 中序遍历. 找2个递减的 第一个是前一个数, 第2个是后一个数, 然后交换; 如果只有1个递减, 则和邻接的下一个交换
     */
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {

        // In order traversal to find the two elements
        traverse(root);

        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void traverse(TreeNode root) {

        if (root == null)
            return;

        traverse(root.left);

        //这2个if比较巧妙, 刚好覆盖到 相邻和不相邻
        // Start of "do some business",
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;

        // End of "do some business"

        traverse(root.right);
    }
}
