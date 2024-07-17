package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q124 {

    /**
     * 懂得轉化成求某個root的最大 左 subtree + 右 subtree + currValue, 就簡單. 視頻.
     * https://www.youtube.com/watch?v=VgbU4D6WuGs&ab_channel=happygirlzt
     * https://www.youtube.com/watch?v=LUWuPSKksCE 講的比較好
     * 思路: 畫圖去理解, 要能想到 實際求的是, 某個root的最大 左 subtree + 右 subtree + currValue.
     */
    int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    // 求 node 爲 root 到 所有葉子節點的 path 可能的最大 sum
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        // 左/右 subtree 最大 path sum
        // 如果是 負數, 只會減少 sum 值, 直接捨棄.
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        // 關鍵: 理解爲何題目求的是: 左 subtree + 右 subtree + currValue
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
