package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if (root == null) return wrapList;

        queue.offer(root);
        while (!queue.isEmpty()) {
            //同一层在同一批次队列中
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            //1层1个 2层2个 3层4个...
            for (int i = 0; i < levelNum; i++) {
                //将下一层入队
                if (queue.peek().left != null) queue.offer(queue.peek().left);
                if (queue.peek().right != null) queue.offer(queue.peek().right);
                //将上一层出队, 组成数组.
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }
}
