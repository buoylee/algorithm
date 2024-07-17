package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q102 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=dRHBcXyCKCY
     * 思路: 按層遍歷, 直接典型 bfs
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null) return result;
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 關鍵: 先保存 當前層的 node 數量, 再從 queue poll
            int levelSize = queue.size();
            List<Integer> currentLevel = new LinkedList<Integer>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null)
                    queue.add(currentNode.left);
                if (currentNode.right != null)
                    queue.add(currentNode.right);
            }
            result.add(currentLevel);
        }
        return result;
    }
}
