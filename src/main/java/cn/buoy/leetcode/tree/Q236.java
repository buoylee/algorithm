package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q236 {
    /**
     * https://www.youtube.com/watch?v=BN2W9r1YW2w
     * 抽象, 从代码理解
     * 如果找到其中之一, 则往上延伸
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果刚好是父子关系, 会一直等到 其他树都返回的时候, 才会确定.
        if (root == null || root == p || root == q) return root;
        //如果本节点是等于2这之一, 则返回值本身.
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //如果左右, 有之一为2者之一, 则往父节点传递, 直到第一次出现左右都 非null 的时候, 就是最低位祖先.
        return left == null ? right : right == null ? left : root;
    }
}
