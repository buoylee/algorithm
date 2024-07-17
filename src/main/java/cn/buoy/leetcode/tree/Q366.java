package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q366 {
    /**
     * 簡單, 知道思路的話, 視頻.
     * https://www.youtube.com/watch?v=7sshPBykr4c 短又好
     * 思路: 從葉子節點開始刪, 直到 "所有元素" 都被刪除. 還是直接看視頻, 看代碼
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // 最頂的 root 都 刪了, 結束
        while (root != null) {
            List<Integer> temp = new ArrayList<>();
            root = removeLeaves(root, temp);
            result.add(temp);
        }
        return result;
    }

    private TreeNode removeLeaves(TreeNode root, List<Integer> temp) {
        if (root == null) return null;
        // 先序遍历
        // 把葉子節點加入 該層的 result list
        // 返回 null, 是爲了 返回上層時, 設置 root.left 和 root.right = null, 這樣就完成了 刪除 "葉子節點"
        if (root.left == null && root.right == null) {
            temp.add(root.val);
            return null;
        }
        // 只有當 left/right 是 "葉子節點" 時, left/right 才會被修改爲 null, 其他情況原樣返回.
        root.left = removeLeaves(root.left, temp);
        root.right = removeLeaves(root.right, temp);
        // 返回了新的root
        return root;
    }

    /**
     * https://www.youtube.com/watch?v=f072MAkKaNo
     * 思路: 從 底到頂 0開始 算level, 中間 有多種可能的值, 取大的作爲 level. 放入 對應的 level list 中即可.
     */
    public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode node, List<List<Integer>> res) {
        if (null == node) return -1;
        // 後序遍歷
        // 关键: 從 底(葉子節點) 到 頂, 從 0開始遞增, 中間 node 取 "所有路徑中可能最大的值" 作爲 level
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        if (res.size() < level + 1) res.add(new ArrayList<>());
        // 插入到相應 level 的 list 中.
        res.get(level).add(node.val);
        return level;
    }
}
