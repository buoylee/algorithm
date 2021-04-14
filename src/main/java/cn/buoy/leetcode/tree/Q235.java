package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //只有 是 root 对 左右node 各自的差 的成绩为 负, 就一定是左右node 中间的值, 才会跳出 while
        //刚好是父子关系的话, 也是成立, 这时等于0, 直接返回.
        while ((root.val - p.val) * (root.val - q.val) > 0)
            //如果 正数 说明 左右node 都在 root的 某一边, 如果root比他们都大, 那root就有左移(变小).
            root = p.val < root.val ? root.left : root.right;
        return root;
    }
}
