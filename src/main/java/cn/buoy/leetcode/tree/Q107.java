package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q107 {
    /**
     * 真的弱智. 可以跳過.
     * https://www.youtube.com/watch?v=hDBBj8ijJm8
     * 思路: 典型 bfs
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null) return result;
        queue.offer(root);
        while (!queue.isEmpty()) {
            //关键: levelSize 就是该层 node 个数, 也是接下来需要 弹出的个数.
            int levelSize = queue.size();
            List<Integer> currList = new LinkedList<Integer>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
                currList.add(curr.val);
            }
            result.add(0, currList);
        }
        return result;
    }

    /**
     * DFS
     * 递归
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level + 1);
        levelMaker(list, root.right, level + 1);
        list.get(list.size() - level - 1).add(root.val);
    }
}
