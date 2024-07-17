package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q333 {
    /**
     * 其實不難, 画图! 快速看視頻, 然後註釋.
     * https://www.youtube.com/watch?v=7Uks17jRAWo
     * // 定義一個 len == 3 的 array, 表示 "某個 node 作爲 BST 時" 他有以下屬性.
     * // return array for each node:
     * //     [0] --> min 這個 tree node 的最小值
     * //     [1] --> max 這個 tree node 的最大值
     * //     [2] --> node number of largest BST in its subtree
     * 思路: 后序递归, 使 root 大于左子树最大值, 且 root 小于右子树最小值, [2]取max(左[2], 右[2]), 不断往上, 到原root.
     */
    public int largestBSTSubtree(TreeNode root) {
        int[] result = largestBST(root);
        return result[2];
    }

    private int[] largestBST(TreeNode node) {
        if (node == null)
            // 使得 "任意 node" 都滿足條件作爲 "此 null" 的 parent(都可以成爲 "合法 BST").
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] left = largestBST(node.left);
        int[] right = largestBST(node.right);
        // 符合 BST, node > 左邊最大, node < 右邊最小
        if (node.val > left[1] && node.val < right[0])
            // 因爲 如果 child 是 null, 會導致 lowerbound == Integer.MAX_VALUE, higherbound == Integer.MIN_VALUE, 所以爲了特別處理 null.
            return new int[]{Math.min(node.val, left[0]),
                    Math.max(node.val, right[1]),
                    left[2] + right[2] + 1};
        else // 關鍵: 不要想歪到, 可能底部有一個大的 subtree, 中間 不合法, 然後 上層又出現合法的 subtree, 因爲上層的 tree 是要包含下層所有的 subtree 合法.
            // 一旦 不是 BTS, lower/higher bound 直接設定爲, 再也無法更新. 所以 "合法的 BST node 數" 也不會再增加.
            return new int[]{Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    Math.max(left[2], right[2])};

    }
}
