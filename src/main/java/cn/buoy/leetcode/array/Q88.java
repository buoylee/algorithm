package cn.buoy.leetcode.array;

public class Q88 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=91bi3bIrW3I
     * 思路: 从后往前排, 超簡單. 不用過多處理位置問題.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 合併後的最後 index
        int lastIdx = m + n - 1;
        // "2個 arr 各自的指針" 移動到 "合法的最後一位 index"
        m--;
        n--;
        // 只要2個 arr 都還有元素, 就需要比.
        while (m >= 0 && n >= 0)
            // 選大放到 lastIdx, 然后 lastIdx--, 被取的那個 arr index--
            nums1[lastIdx--] = nums2[n] > nums1[m] ? nums2[n--] : nums1[m--];
        // 如果是 "長的 arr"(num1) 取到頭了, 即, num2 還有剩(n >= 0), 就繼續用 剩餘的 把 num1 填滿就好. 如果是 num1 有剩, 不用任何處理, 就是原位.
        while (n >= 0)
            nums1[lastIdx--] = nums2[n--];
    }

}
