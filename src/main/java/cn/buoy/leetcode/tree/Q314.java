package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.*;

public class Q314 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=sXZGaIlMTZI (失效)
     * https://www.youtube.com/watch?v=AbR_-FO3HDE
     * 思路: 先計算出需要多寬的 result list(前序遍歷即可找到 相對於 "最頂 root" 爲0 的 最左/右 index);
     * 然後 再次從"最頂 root" bfs 遍歷所有 node 放入 "對應 col" 的 list 中(實際的col index == 上邊算的 "相對 index" - 上邊算的 min(最左)值)
     */
    int min = 0;
    int max = 0;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        // 以 "最頂root" 作爲 參考x座標0, 找到相對於 "最頂root" 的 最左/右 x座標.
        helper(root, 0);
        for (int i = min; i <= max; i++)
            result.add(new ArrayList<>());
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();
        nodeQueue.offer(root);
        // 關鍵: root 相對 result[] index 就是 -min
        // -3 -2 -1 0 1 2
        //  0  1  2 3 4 5
        indexQueue.offer(-min);
        // 細微差別的 tree bfs, 不需要分層. poll
        while (!nodeQueue.isEmpty()) {
            TreeNode curr = nodeQueue.poll();
            int currIndex = indexQueue.poll();
            result.get(currIndex).add(curr.val);
            if (curr.left != null) {
                nodeQueue.offer(curr.left);
                indexQueue.offer(currIndex - 1);
            }
            if (curr.right != null) {
                nodeQueue.offer(curr.right);
                indexQueue.offer(currIndex + 1);
            }
        }
        return result;
    }

    // 以 "最頂root" 作爲 參考x座標0, 找到相對於 "最頂root" 的 最左/右 x座標.
    private void helper(TreeNode root, int rootIdx) {
        if (root == null) return;
        min = Math.min(min, rootIdx);
        max = Math.max(max, rootIdx);
        helper(root.left, rootIdx - 1);
        helper(root.right, rootIdx + 1);
    }
}
