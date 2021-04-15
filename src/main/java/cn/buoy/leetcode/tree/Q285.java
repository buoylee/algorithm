package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q285 {
    /**
     * BST 后继node, 关键在于, 如果如果不在parent 和 right时, 要找到 右边子树的 最小值.
     * 画图理解
     * 如果 root 大于 目标, 先向左(小)找, 直到找到 比目标小的, 然后 在子树里往大找, 第一个大于的就是. 如果子树都没有, 那就是 本质root本身.
     * 如果 root 小于 目标, 向右找, 直到 找到 大于目标的; 然后向左, 假如最后没找到 小于 目标的, 说明 这个点...
     * 画一下后继点的情况图, 就好.
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            //如果找不到, 这里是最后一个还向左的root,这个点就是后续node.
            //要画图
            TreeNode left = successor(root.left, p);
            //如果left 不等于 空, 就是有解,
            return (left != null) ? left : root;
        }
    }
}
