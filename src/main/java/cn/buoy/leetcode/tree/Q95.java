package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q95 {
    /**
     * 簡單, 自己能想到的
     * https://www.youtube.com/watch?v=gi_Dfz05T6Q&t=15s 代碼加入了 memoization("start,end" 作爲 key), 其他一樣.
     * 思路: for (1~n), 取任一個作爲root, 然後遞歸2邊.
     */
    private Map<String, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return genTrees(1, n);
    }

    private List<TreeNode> genTrees(int start, int end) {
        String key = start + "," + end;
        if (memo.containsKey(key))
            return memo.get(key);
        List<TreeNode> list = new ArrayList<>();
        // 越界時, 需要加入 null 節點, 題目要求.
        if (start > end) {
            list.add(null);
            return list;
        }
        // 每一個 node 作爲 root 的 情況.
        for (int i = start; i <= end; i++) {
            // 左右子樹遞歸
            List<TreeNode> left = genTrees(start, i - 1);
            List<TreeNode> right = genTrees(i + 1, end);
            // 組合出 key = "start,end" 範圍內, 以 i 爲 root, 所有可能的樹, 放入 map<key, treeList> 的 list 中.
            for (TreeNode lnode : left) {
                for (TreeNode rnode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        // memoization
        memo.put(key, list);
        return list;
    }

    /**
     * 分治寫法2, 上邊寫法比較直觀, 清晰
     * https://www.youtube.com/watch?v=GZ0qvkTAjmw
     */
    public List<TreeNode> generateTrees2(int n) {

        return genTrees2(1, n);
    }

    public List<TreeNode> genTrees2(int start, int end) {

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
