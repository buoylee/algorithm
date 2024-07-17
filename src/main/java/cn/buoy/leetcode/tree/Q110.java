package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q110 {
    /**
     * 簡單, 知道如何判斷平衡就簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=Be5CUodZliM
     * https://www.youtube.com/watch?v=7vRTOS2SMuk 短
     * 思路: 遞歸檢查 "每個 node" 的 "左/右子樹"的高度來判斷 node 是否是平衡樹的 root, 這樣是可以的.
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftHeight = helper(root.left);
        int rightHeight = helper(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) return false;
        // 關鍵: 超過高度差的 left/right, 是可以出現在 subtree 中的, 而不在上層 node.
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 求當前 node 最大高度
     */
    private int helper(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(helper(root.left), helper(root.right));
    }

    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root) != -1;
    }

    private int helper2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper2(root.left);
        int right = helper2(root.right);
        //关键在这, -1 是由 Math.abs(left - right) > 1) 造成, 即大于 1 的时候, 快速返回.
        //从底往上加, 高度差一旦 大于 1 , 马上返回.
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }
}
