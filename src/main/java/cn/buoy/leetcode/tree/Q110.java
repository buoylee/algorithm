package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q110 {
    /**
     * https://www.youtube.com/watch?v=Be5CUodZliM&ab_channel=basketwangCoding
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root) != -1;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        //关键在这, -1 只有可能 Math.abs(left - right) > 1) 造成, 即大于 1 的时候, 快速返回.
        //从底往上加, 高度差一旦 大于 1 , 马上返回.
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
