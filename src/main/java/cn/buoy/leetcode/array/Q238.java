package cn.buoy.leetcode.array;

public class Q238 {
    /**
     * 看視頻, 知道方法就簡單.
     * https://www.youtube.com/watch?v=rpQhKorJRd8
     * 思路: 1. 左到右累乘, 使得 res[i] = nums(0~i-1)相乘
     * 2. 右到左累乘, 使得 res[i] = nums(i+1~nums.len-1)相乘
     * 3. 這時, 其實就有了所需的 左累乘 和 右累乘
     * 4. 實際代碼中, 左到右累乘 放在 res 中, 右到左累乘 由一個 right 變量存儲, 當完成 2遍 遍歷相乘後, 就是結果.
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        // 因为 result[0] 不包含 nums[0] 的累乘, 所以初始是 1.
        result[0] = 1;
        // 第一步: 累乘 i 左边所有元素, 并放入res
        for (int i = 1; i < len; i++)
            result[i] = result[i - 1] * nums[i - 1];
        // 开始从尾开始 右边累乘, 因为是乘法, 且不包含 nums 最后一位, 初始为 1.
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            // 第一輪的左累乘 * 現在的right(右累乘) 就是結果.
            result[i] *= right;
            // 更新右累乘
            right *= nums[i];
        }
        return result;
    }
}
