package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Q103 {
    /**
     * 簡單, 視頻, 思路一樣, 實現有點不同.
     * https://www.youtube.com/watch?v=KFkjJ7pjWVw
     * 思路: 典型 bfs, 只需要在 奇數行 反向poll/push即可.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean leftToRight = true;
        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            List<Integer> currLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                if (leftToRight) { // 左到右, 左 poll,  push
                    TreeNode node = deque.pollFirst();
                    currLevel.add(node.val);
                    if (node.left != null)
                        deque.offerLast(node.left);
                    if (node.right != null)
                        deque.offerLast(node.right);
                } else { // 右到左, 右 poll, 左 push
                    TreeNode node = deque.pollLast();
                    currLevel.add(node.val);
                    if (node.right != null)
                        deque.offerFirst(node.right);
                    if (node.left != null)
                        deque.offerFirst(node.left);
                }
            }
            result.add(currLevel);
            leftToRight = !leftToRight;
        }
        return result;
    }

    /**
     * 遞歸寫法 不夠上邊直觀
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        travel(root, result, 0);
        return result;
    }

    /**
     * dfs(連上下 level 都是zigzag)
     */
    private void travel(TreeNode curr, List<List<Integer>> result, int level) {
        if (curr == null) return;
        // 當 level 進入下一層時, 插一個 list 進 result
        if (result.size() <= level) {
            List<Integer> newLevel = new LinkedList<Integer>();
            result.add(newLevel);
        }

        List<Integer> collection = result.get(level);
        if (level % 2 == 0)
            collection.add(curr.val);
        else
            collection.add(0, curr.val);

        travel(curr.left, result, level + 1);
        travel(curr.right, result, level + 1);
    }
}
