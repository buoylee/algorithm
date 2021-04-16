package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q222 {

    /**
     * https://www.youtube.com/watch?v=tXe1NS4fgO4
     * 有点抽象, 每次都看左右子树, 如果左右子树高度相等, 可以得出(画图就知道了!), 左子树肯定是满的, 这时只要统计右子树节点 + 左满子树 就好; 否则 反之.
     *
     * @param root
     * @return
     */
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
                height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                        : (1 << h - 1) + countNodes(root.left);
    }
}
