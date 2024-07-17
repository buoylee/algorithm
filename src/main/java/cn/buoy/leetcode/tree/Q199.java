package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q199 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=nxQzcx_m700
     * 思路: bfs, 從右到左 offer node 到 queue, 每一層 第一次 poll的時候, 就是我們要得 node.
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        if (root == null) return result;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                // 第一个元素, 就是这一层最右边的元素.
                if (i == 0)
                    result.add(cur.val);
                // 从右往左加入
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }
        }
        return result;
    }

    // 上邊的比較直觀
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        //先打印本node, 再往下走
        if (currDepth == result.size()) {
            result.add(curr.val);
        }

        //从右到左优先插入
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }
}
