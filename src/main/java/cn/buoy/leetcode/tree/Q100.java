package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

/**
 * 简单, 視頻, 代碼
 * https://www.youtube.com/watch?v=QmJ6Lo1ngFQ
 * 思路: 遞歸判斷 2tree 的 left/right node 的 value 是否相等.
 */
public class Q100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }
}
