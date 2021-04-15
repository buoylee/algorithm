package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q270 {
    /**
     * https://www.youtube.com/watch?v=Y5ga1T04UNA
     * 搜索树特点, 如果root 比 node 大,  结果 只能出现在 左子树中, 只需往下找就好, 范围只会越来越小.
     *
     * @param root
     * @param target
     * @return
     */
    public int closestValue(TreeNode root, double target) {
        int ret = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - ret)) {
                ret = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return ret;
    }
}
