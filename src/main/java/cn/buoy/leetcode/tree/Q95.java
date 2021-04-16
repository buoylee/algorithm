package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q95 {
    /**
     * https://www.youtube.com/watch?v=GZ0qvkTAjmw
     * 在dp里, 算简单
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {

        return genTrees(1, n);
    }

    public List<TreeNode> genTrees(int start, int end) {

        List<TreeNode> list = new ArrayList<TreeNode>();

        if (start > end) {
            list.add(null);
            return list;
        }

        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }

        List<TreeNode> left, right;
        //也是分左右子树list, 然后组合.
        for (int i = start; i <= end; i++) {

            left = genTrees(start, i - 1);
            right = genTrees(i + 1, end);

            for (TreeNode lnode : left) {
                for (TreeNode rnode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }

        }

        return list;
    }
}
