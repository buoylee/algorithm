package cn.buoy.leetcode.design;

public class Q303 {
    /**
     * 簡單(只要不用修改 arr 的元素, follow up 307), 視頻
     * https://www.youtube.com/watch?v=pujaCH4UjYw
     * 思路: 前綴和. 求區間 [i~j] pre[j] - pre[i-1], 但是 [0, 3] 沒有 -1, 所以, 爲了代碼方便, pre 需要多一個 dummyHead.
     */
    public class NumArray {
        private int[] prefixSums;

        public NumArray(int[] nums) {
            // 爲了方便計算 [0, x] 這樣從 0 開始的 sum, prefixSums.len 多一個dummy head 0.
            prefixSums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++)
                prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        public int sumRange(int i, int j) {
            // 原理: [i~j] pre[j] - pre[i-1], 但是爲了 方便計算 [0, x] 這類區間.
            return prefixSums[j + 1] - prefixSums[i];
        }
    }
}


