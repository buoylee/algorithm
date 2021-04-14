package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q108 {
    /**
     * https://www.youtube.com/watch?v=Dqn_y6vkoIg
     *
     * @param num
     * @return
     */
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode head = helper(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helper(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        //选择合适的数(中位数), 置为中间node. 将左右分配对给左右node.
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }
}
