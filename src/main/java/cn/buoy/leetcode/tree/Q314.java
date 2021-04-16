package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.*;

public class Q314 {
    /**
     * https://www.youtube.com/watch?v=sXZGaIlMTZI
     * 简单, 忘了就画个图.
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        //同时推入 node 和 所在层数(root 为 0, 左减右加).
        q.add(root);
        cols.add(0);

        int min = 0;
        int max = 0;

        //基本的层序迭代
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);

            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }

            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }

        return res;
    }
}
