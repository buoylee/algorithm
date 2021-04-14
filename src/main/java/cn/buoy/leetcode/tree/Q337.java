package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q337 {
    /**
     * https://www.youtube.com/watch?v=-i2BFAU25Zk&t=2s
     * <p>
     * 偷房子1, 2, 3, 这个视频 动态规划 很好, 很清楚.
     */
    public int rob(TreeNode root) {
        int[] res = robSub(root);
        //0 表示 这个node 不偷
        //1 表示 这个node 偷
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        //不偷, 则它的左右node 都可以选择 偷或不偷, 所以分别取的 左右node 的 最大值.
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //偷了, 则只有1种情况, 就是左右node 都不能偷了, 再加上 本node 的值.
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
