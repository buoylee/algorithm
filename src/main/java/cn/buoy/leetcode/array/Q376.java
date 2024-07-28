package cn.buoy.leetcode.array;

public class Q376 {
    /**
     * 简单, 視頻
     * https://www.youtube.com/watch?v=eOVP7fudeNg
     * https://www.bilibili.com/video/BV1cZ4y1A7Pn?from=search&seid=8232337475929480634
     * 思路: dp, wiggle node 與 node 之間, 有遞增/遞減2種變化可能,
     * 我們需要記錄2種 dp 狀態 dpUp[i], dpDown[i]
     * dpUp[i] 代表, 到達 i 位置, 最後一次(nums[j - 1] 到 nums[j], 注意 j 可以是 i及之前 所有位置可能) 是遞增的最長 Wiggle subSeq 長度;
     * 同理, dpDown[i]
     * 我們可以根據 nums[i - 1] 和 nums[i] 確定當前的變化類型(遞增/遞減), 然後找到以往相反狀態的最長長度 + 1 作爲 當前 dp[i] 即可.
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) return 0;
        // dpUp[i] 代表, 到達 i 位置, 最後一次(nums[j - 1] 到 nums[j], 注意 j 可以是 i及之前 所有位置可能) 是遞增的最長 Wiggle subSeq 長度.
        int[] dpUp = new int[nums.length];
        // 同理
        int[] dpDown = new int[nums.length];
        //无论up/down 初始 元素 本身 就是長度1.
        dpUp[0] = 1;
        dpDown[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            // 如果 nums[j - 1] 到 nums[j] 是递增
            if (nums[i - 1] < nums[i]) {
                dpUp[i] = dpDown[i - 1] + 1; // 更新 dpUp, "前一個 dpDown" +1
                dpDown[i] = dpDown[i - 1]; // 因为當前是递增, 所以無法給 dpUp 增加長度, 和 dpDown[i - 1] 一样.
            } else if (nums[i - 1] > nums[i]) { // 同理
                dpDown[i] = dpUp[i - 1] + 1;
                dpUp[i] = dpUp[i - 1];
            } else {
                // num 相等就 直接复制貼上 "上一次的 dp"
                dpDown[i] = dpDown[i - 1];
                dpUp[i] = dpUp[i - 1];
            }
        }
        // 取 末尾(nums.length - 1) 较大的 dp 即可.
        return Math.max(dpDown[nums.length - 1], dpUp[nums.length - 1]);
    }
}
