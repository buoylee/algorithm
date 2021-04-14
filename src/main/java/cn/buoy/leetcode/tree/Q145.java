package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Q145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        if (root == null) return pre;
        pre.addAll(postorderTraversal(root.left));
        pre.addAll(postorderTraversal(root.right));
        pre.add(root.val);
        return pre;
    }
}
