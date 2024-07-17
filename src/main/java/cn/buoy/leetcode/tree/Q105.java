package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Q105 {
    /**
     * 簡單, 聽懂思路的話
     * https://www.youtube.com/watch?v=6Xcz08RkRHE 講的很清楚
     * 思路: 根據 inorder(第一個是root) 和 preorder(從 inorder 確認了 root, 就能切分出 root 的 left/right subtree), 一直遞歸完所有 node.
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderVKMap = new HashMap<Integer, Integer>();
        // 題目表示 元素都是唯一, 所以可以存 map
        for (int i = 0; i < inorder.length; i++)
            inorderVKMap.put(inorder[i], i);
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderVKMap);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> valIdxMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        // preorder 第一個 node 是 root
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = valIdxMap.get(root.val);
        // 左子樹的 node 的數量. 用於計算 preorder 中, 左/右子樹的範圍. 畫圖會非常容易理解.
        int leftTreeNodeSize = rootIdx - inStart;
        root.left = buildTree(preorder, preStart + 1, preStart + leftTreeNodeSize, inorder, inStart, rootIdx - 1, valIdxMap);
        root.right = buildTree(preorder, preStart + leftTreeNodeSize + 1, preEnd, inorder, rootIdx + 1, inEnd, valIdxMap);
        return root;
    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34543/Simple-O(n)-without-map
     * 时间, 空间更优
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private int in = 0;
    private int pre = 0;

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length) return null;
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }
}
