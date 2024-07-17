package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q222 {
    /**
     * 就用簡單的 bfs就好了(leetcode 可以過嗎?),
     * 下邊方法, 記得這是"子樹"遞歸檢查 是否是完全2叉樹. 不要搞混.
     * https://www.youtube.com/watch?v=tXe1NS4fgO4
     * https://www.youtube.com/watch?v=Sc9xjThM1Ag 短, 相對清楚
     * 思路: 檢查 左右 高度 是否相同 來判斷 tree 是否是 完全2叉樹.
     * 是, 則快速得出 "總 node 數", (1 << left) - 1
     * 不是, 則 遞歸 左/右子樹 做同樣操作, 一直不同, 則拆到葉子節點爲止.
     */
    public int countNodes(TreeNode root) {
        int left = leftDepth(root);
        int right = rightDepth(root);
        if (left == right)
            return (1 << left) - 1;
        else
            // 1 是 root node
            return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 左邊緣 長度, 包括 root
    private int leftDepth(TreeNode root) {
        int res = 0;
        while (root != null) {
            root = root.left;
            res++;
        }
        return res;
    }

    // 右邊緣 長度, 包括 root
    private int rightDepth(TreeNode root) {
        int res = 0;
        while (root != null) {
            root = root.right;
            res++;
        }
        return res;
    }
}
