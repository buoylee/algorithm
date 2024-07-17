package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q111 {
    /**
     * 不要以爲和 算最大高度 一樣的思路, 完全不同. 要理解 有 leaf 才能算深度這個原理才簡單, 視頻
     * https://www.youtube.com/watch?v=Yio16ATjGYM
     * 思路: 畫圖, 看高度是怎麼累積的, 才好理解,
     * 當一個 root 只有 left 或 right node 時(left/right都是 null 同理), 累積的方式是 長的那邊 + 1;
     * 當一個 root 的 left 和 right node 都存在時, 才是取 小的 left 或 right + 1;
     * 沒有什麼難得, 只要畫圖就能理解, 就會得出上邊的規律.
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // 只有 left 或 right node 時(left/right都是 null 同理), 累積的方式是 長的那邊 + 1
        if (root.left == null || root.right == null)
            // 關鍵: 要找到leaf才能算深度, 因此取最大才能找到leaf
            return 1 + Math.max(minDepth(root.left), minDepth(root.right));
        // 當 left 和 right node 都存在時, 取 小的 left 或 right + 1
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth2(root.left);
        int right = minDepth2(root.right);
        // 关键: 當一個 root 只有 left 或 right node 時(left/right都是 null 同理), 累積的方式是 長的那邊 + 1;
        return (left == 0 || right == 0) ?
                left + right + 1 // 反正有一邊是0, 方便計算.
                : Math.min(left, right) + 1; // 當一個 root 的 left 和 right node 都存在時, 才是取 小的 left 或 right + 1

    }
}
