package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

public class Q99 {
    /**
     * 簡單? 視頻, 註釋, 特例(只出現1次err(遞減)swap方式)的原理未知.
     * https://www.youtube.com/watch?v=sDQhHZJkNh0 這個畫圖講的比較清楚, 容易理解(只有1次遞減的特例, 沒有講清楚原因), 長
     * https://www.youtube.com/watch?v=SkAi1PVkkw0 短, 都沒有講清楚特例.
     * 思路: 中序遍历(遞增)檢查, 找2个 pre-root 和 root 递减的情況, 交換 這2個 pre-root;
     * 特例: 如果 只找到 1個 pre-root 和 root 递减的情況 只需要交換這個 pre-root 和 root 的位置, 原因看下邊註釋.
     */
    // 第一次出現 pre-root > root 的情況時的 pre-root
    TreeNode firstNextEleDecrease = null;
    // 有2種情況: 1. 出現 第2次 pre-root > root 的情況的 pre-root; 2. 不再出現, 那 secondEle 就是 第一次出現時的 root. 原理看下邊註釋(未解決).
    TreeNode secondEle = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevEle = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        // In order traversal to find the two elements
        traverse(root);
        // Swap the values of the two nodes
        // 關鍵: 只需要交換 node valve, left/right 引用都是對的.
        int temp = firstNextEleDecrease.val;
        firstNextEleDecrease.val = secondEle.val;
        secondEle.val = temp;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        // Start of "do some business",
        // If first element has not been found, assign it to prevElement
        if (firstNextEleDecrease == null // 只有確定找到 firstNextEleDecrease, 才能 賦值給 secondEle
                && prevEle.val >= root.val) // 發現遞減 node
            firstNextEleDecrease = prevEle;
        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        // 這裏其實包擴2種情況:
        // 1. 2個明顯錯誤點(pre-root 對比 root 是遞減) 的情況;
        // 關鍵: 2. 只出現1次遞減情況.
        // 按題目要求, 只需要 交換 有且只有1次 位置, 因爲 2叉搜索樹的 結構導致, 如果出現"情況2", 只可能是 pre-root 與 root 和 對調. 這個後續需要釐清原因.
        if (firstNextEleDecrease != null && prevEle.val >= root.val)
            secondEle = root;
        prevEle = root;
        // End of "do some business"
        traverse(root.right);
    }
}
