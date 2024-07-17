package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.HashMap;

public class Q106 {
    /**
     * 簡單, 同105, 看懂其中一題就好.
     * https://www.youtube.com/watch?v=euO5pWQtqNQ
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> inorderVKMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; ++i)
            inorderVKMap.put(inorder[i], i);
        return buildTree(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1,
                inorderVKMap);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                               int[] postorder, int postStart, int postEnd,
                               HashMap<Integer, Integer> inorderVKMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIdx = inorderVKMap.get(postorder[postEnd]);
        // 左子樹的 node 的數量. 用於計算 preorder 中, 左/右子樹的範圍. 畫圖會非常容易理解.
        int leftTreeNodeSize = rootIdx - inStart;
        root.left = buildTree(inorder, inStart, rootIdx - 1,
                postorder, postStart, postStart + leftTreeNodeSize - 1,
                inorderVKMap);
        root.right = buildTree(inorder, rootIdx + 1, inEnd,
                postorder, postStart + leftTreeNodeSize, postEnd - 1,
                inorderVKMap);
        return root;
    }
}
