package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q113 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=thQmr2pYGss
     * 思路: 典型回溯.
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path, result);
        return result;
    }

    private void dfs(TreeNode node, int remain, List<Integer> temp, List<List<Integer>> result) {
        if (node == null) return;
        temp.add(node.val);
        // reach a leaf node
        if (node.left == null && node.right == null && remain == node.val) {
            result.add(new ArrayList<>(temp));
        } else {
            dfs(node.left, remain - node.val, temp, result);
            dfs(node.right, remain - node.val, temp, result);
        }
        // backtrack
        temp.remove(temp.size() - 1);
    }

    /**
     * 上邊寫法比較簡潔.
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> currentResult = new LinkedList<Integer>();
        pathSum2(root, sum, currentResult, result);
        return result;
    }

    public void pathSum2(TreeNode root, int sum, List<Integer> currentResult,
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
            pathSum2(root.left, sum - root.val, currentResult, result);
            pathSum2(root.right, sum - root.val, currentResult, result);
        }
        //递归返回时, 每层走完都要回溯, 回到上一层再往下走
        currentResult.remove(currentResult.size() - 1);
    }
}
