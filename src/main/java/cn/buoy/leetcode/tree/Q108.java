package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q108 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=Dqn_y6vkoIg
     * 思路: 直接2分就可以了, 遞歸時 subtree 取 "subArr 中點" 作爲 root , 就不會存在像 linklist 一樣, 造成不平衡.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        TreeNode root = helper(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode helper(int[] num, int low, int high) {
        // 一個 node 都不剩
        if (low > high)
            return null;
        // 选 中位数 爲 root. 遞歸 左/右 subtree.
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }
}
