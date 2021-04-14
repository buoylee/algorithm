package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q129 {
    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode n, int s) {
        if (n == null) return 0;
        //上层算好的前n位数 + 上最后一位, 的结果返回, 有几个叶子节点返回几次
        if (n.right == null && n.left == null) return s * 10 + n.val;
        //运用了 递归 会记录每一层的加过的值,
        //将每个左右节点都sum后, 返回.
        return sum(n.left, s * 10 + n.val) + sum(n.right, s * 10 + n.val);
    }
}
