package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Q113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> currentResult = new LinkedList<Integer>();
        pathSum(root, sum, currentResult, result);
        return result;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> currentResult,
                        List<List<Integer>> result) {

        if (root == null)
            return;
        currentResult.add(new Integer(root.val));
        if (root.left == null && root.right == null && sum == root.val) {
            result.add(new LinkedList(currentResult));
            //回溯
            currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
            return;
        } else {
            pathSum(root.left, sum - root.val, currentResult, result);
            pathSum(root.right, sum - root.val, currentResult, result);
        }
        //递归返回时, 每层走完都要回溯, 回到上一层再往下走
        currentResult.remove(currentResult.size() - 1);
    }
}
