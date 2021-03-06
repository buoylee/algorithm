package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q199 {
    public List<Integer> rightSideView(TreeNode root) {
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
