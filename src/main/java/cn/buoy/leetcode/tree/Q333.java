package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q333 {
    //该子树的 范围 [2] 是包含该node的 node 个数
    // return array for each node:
    //     [0] --> min
    //     [1] --> max
    //     [2] --> largest BST in its subtree(inclusive)

    /**
     * https://www.youtube.com/watch?v=7Uks17jRAWo
     * 画图!一定!
     * 后序递归, 使 root 大于左子树最大值, 且 root 小于右子树最小值, [2]取max(左[2], 右[2]), 不断往上, 到原root.
     *
     * @param root
     * @return
     */
    public int largestBSTSubtree(TreeNode root) {
        int[] ret = largestBST(root);
        return ret[2];
    }

    private int[] largestBST(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = largestBST(node.left);
        int[] right = largestBST(node.right);
        if (node.val > left[1] && node.val < right[0]) {
            return new int[]{Math.min(node.val, left[0]), Math.max(node.val, right[1]), left[2] + right[2] + 1};
        } else {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])};
        }
    }
}
