package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q366 {
    /**
     * https://www.youtube.com/watch?v=f072MAkKaNo
     *
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode node, List<List<Integer>> res) {
        if (null == node) return -1;
        //关键在这, 这里求得高度是 最远的叶子节点(叶子节点高度为0)到这个点的高度, 即某个node的最大可能的高度.
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        if (res.size() < level + 1) res.add(new ArrayList<>());
        res.get(level).add(node.val);
        return level;
    }
}
