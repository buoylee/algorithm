package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q235 {
    /**
     * 知道規律就簡單.
     * https://www.youtube.com/watch?v=taTHaoOwsf0
     * 思路: 從root開始找, 剛好 curr 在 p, q 數值之間, 就是正確答案. 一些疑問, 畫圖就好理解.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else // curr 等於 p, q 之一 也沒問題.
            return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //只有 是 root 对 左右node 各自的差 的成绩为 负, 就一定是左右node 中间的值, 才会跳出 while
        //刚好是父子关系的话, 也是成立, 这时等于0, 直接返回.
        while ((root.val - p.val) * (root.val - q.val) > 0)
            //如果 正数 说明 左右node 都在 root的 某一边, 如果root比他们都大, 那root就有左移(变小).
            root = p.val < root.val ? root.left : root.right;
        return root;
    }
}
