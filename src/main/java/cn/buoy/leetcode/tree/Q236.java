package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q236 {
    /**
     * 要仔細想, 視頻
     * https://www.youtube.com/watch?v=BN2W9r1YW2w
     * https://www.youtube.com/watch?v=T-PGz0jnAHA 好
     * 思路: 遞歸往下層尋找 p/q, 一旦發現 返回給上層提供選擇.
     * 有2種情況:
     * 1. 返回上層途中, 一旦發現 某個 root 的 left 和 right 是 p/q 或 q/p, 最快發現的 common parent, 就是最小的.(最總返回到 最上層 root);
     * 2. p, q 是 父子節點關係,  只會有left/right 返回上層(不會有 left 和 right 是 p/q 或 q/p 的情況), 最早遇到的 p/q, 就是 common parent.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // == null, 說明 該子樹 不存在 p/q, 上層會選擇 == p/q 的, 繼續上傳;
        // == p/q 說明 公共父節點 存在于該子樹;
        // 這些 root 情況, 向上 傳遞.
        if (root == null || root == p || root == q) return root;
        // 後續遍歷中的 子節點 遞歸
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 當下層一旦出現 left/right 都不爲空時, 就是 最小的 common parent.
        if (left != null && right != null)
            return root;
        // 如果 p, q 是 父子節點關係, 結果也是對的, 因爲會返回第一個 == p/q的 node.
        return left == null ? right : left;
    }
}
