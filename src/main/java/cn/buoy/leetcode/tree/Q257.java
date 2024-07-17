package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q257 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=hKeDLTcTZEs
     * 思路: 典型 前序遍歷"curr + root.val".
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, "");
        return res;
    }

    private void helper(List<String> res, TreeNode root, String curr) {
        // 細節: 這裏是爲了, 省略 下邊 dfs 時, root.left 和 root.right 的 != null 判斷.
        if (root == null) return;
        // 到達 葉子節點
        if (root.left == null && root.right == null) {
            res.add(curr + root.val);
            return;
        }
        helper(res, root.left, curr + root.val + "->");
        helper(res, root.right, curr + root.val + "->");
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    private void helper(List<String> res, TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            helper(res, root.left, sb);
            helper(res, root.right, sb);
        }
        //关键 简单 回溯
        sb.setLength(len);
    }

    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> answer = new ArrayList<String>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }

    private void searchBT(TreeNode root, String path, List<String> answer) {
        //叶子节点 等于 前边的 string + 当前的value, 返回上一层执行, string + (string + (string + value))...
        if (root.left == null && root.right == null) answer.add(path + root.val);
        //其他节点则, 加好后放到下一层递归
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }


}
