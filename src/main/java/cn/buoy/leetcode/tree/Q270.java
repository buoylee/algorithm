package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q270 {
    /**
     * 簡單, 當2分搜索來看.
     * https://www.youtube.com/watch?v=Y5ga1T04UNA
     * 搜索树特点, 如果root 比 node 大,  结果 只能出现在 左子树中, 只需往下找就好, 范围只会越来越小.
     */
    public int closestValue(TreeNode curr, double target) {
        int closest = curr.val;
        while (curr != null) {
            // 發現 "更小的差值" 就更新
            if (Math.abs(target - curr.val) < Math.abs(target - closest))
                closest = curr.val;
            // curr 小了就往右, 大了就往左
            curr = curr.val > target ? curr.left : curr.right;
        }
        return closest;
    }
}
