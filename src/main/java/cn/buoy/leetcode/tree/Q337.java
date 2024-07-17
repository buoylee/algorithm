package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q337 {
    /**
     * 偷房子1, 2, 3, 这个视频 动态规划 很好, 很清楚.
     * https://www.youtube.com/watch?v=-i2BFAU25Zk&t=2s
     * 思路: 從下往上, 計算 本 node 偷與不偷的 值,
     * 到達上層 root 時, 同樣分 偷與不偷 2種情況,
     * root 不偷, 則 left/right 都可以 偷, 選擇最大的 left 和 right 的值, 相加, 即是;
     * root 偷, 则 left/right 都只能選 "不偷" 的值, 再加上 root 的值, 即是;
     */
    public int rob(TreeNode root) {
        int[] res = robSub(root);
        // int[0] 表示 这个node 不偷
        // int[1] 表示 这个node 偷
        // 選大的就是結果
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] result = new int[2];
        // 關鍵: 得到 left/right後, 開始處理他們的 root
        // root 如果選擇不偷, 则 root 的左右 node 都可以选择 偷或不偷, 所以分别取的 左 和 右的 最大值.
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // root 如果選擇偷, 则 左/右 node 都只能選 "不偷", 再加上 本node 的值.
        result[1] = root.val + left[0] + right[0];
        return result;
    }
}
