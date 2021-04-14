package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Q144 {
    /**
     * 迭代
     *
     * @param node
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> rights = new Stack<>();
        while (node != null) {
            //先add node value , 入栈右子节点, 继续迭代左节点, 直到左节点为null, 开始 从底到顶, pop出 右节点.
            list.add(node.val);
            if (node.right != null) {
                rights.push(node.right);
            }
            node = node.left;
            if (node == null && !rights.isEmpty()) {
                node = rights.pop();
            }
        }
        return list;
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        if (root == null) return pre;
        pre.add(root.val);
        pre.addAll(preorderTraversal(root.left));
        pre.addAll(preorderTraversal(root.right));
        return pre;
    }

    /**
     * 递归2
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        preHelper(root, pre);
        return pre;
    }

    public void preHelper(TreeNode root, List<Integer> pre) {
        if (root == null) return;
        pre.add(root.val);
        preHelper(root.left, pre);
        preHelper(root.right, pre);
    }

    /**
     * 迭代2
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal4(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        if (root == null) return pre;
        Stack<TreeNode> tovisit = new Stack<TreeNode>();
        tovisit.push(root);
        while (!tovisit.empty()) {
            TreeNode visiting = tovisit.pop();
            pre.add(visiting.val);
            if (visiting.right != null) tovisit.push(visiting.right);
            if (visiting.left != null) tovisit.push(visiting.left);
        }
        return pre;
    }

}
