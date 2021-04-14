package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Q94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        if (root == null) return pre;
        pre.addAll(inorderTraversal(root.left));
        pre.add(root.val);
        pre.addAll(inorderTraversal(root.right));
        return pre;
    }
}
