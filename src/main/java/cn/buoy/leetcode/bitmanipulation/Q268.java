package cn.buoy.leetcode.bitmanipulation;

public class Q268 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=yMvY_ZOFUeQ
     * 思路: 还是抑或, 利用了 index 和 value 應該一一對應的這個條件,
     * 關鍵: 實際上arr.length == n([0~n]完整的長度應該位n+1), 我們要找的也是0~n中缺少的那個數,
     * 所以 index 其實 少 ^ 了一個值, 就是nums.length.
     */
    public int missingNumber(int[] nums) {
        // 關鍵: nums 中的值的範圍是 0~nums.length, 而 nums 的 index 肯定沒有 nums.length 這個index, 所以要補上.
        int result = nums.length;
        for (int i = 0; i < nums.length; i++)
            result = result ^ i ^ nums[i];
        return result;
    }
}
