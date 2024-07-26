package cn.buoy.leetcode.array;

public class Q26 {
    /**
     * 簡單, 視頻, 同283
     * https://www.youtube.com/watch?v=nSYALuTFsP4
     * 思路: 定義一個 handlingIdx 指針, 表示 出現與 "handlingIdx - 1" 不重複的元素時, "待填充的 index",
     * 一旦 i 遍歷到與 "handlingIdx - 1" 不同的元素, 則複製給 arr[handlingIdx], 然後 handlingIdx++; 直至 i 到頭.
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;
        // 因爲是檢查是否重複, 所以從 index == 1 開始檢查.
        int handlingIdx = 1;
        for (int i = 1; i < len; ++i)
            // i 與 handlingIdx 前一位比較
            if (nums[i] != nums[handlingIdx - 1])
                nums[handlingIdx++] = nums[i];
        return handlingIdx; // 上邊多++了一次, 但是因爲返回的是 len, 所以剛好不用處理.
    }

    // 看上邊就好
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1])
                count++;
            else
                nums[i - count] = nums[i];
        }
        return n - count;
    }
}
