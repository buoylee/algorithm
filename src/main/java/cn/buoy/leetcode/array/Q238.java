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
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        //第一步:累乘 i左边 元素, 并放入res
        for (int i = 1; i < n; i++)
            res[i] = res[i - 1] * nums[i - 1];
        //开始从尾开始 右边累乘,
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            // 第一輪的左累乘 * 現在的right(右累乘) 就是結果.
            res[i] *= right;
            // 更新右累乘
            right *= nums[i];
        }
        return res;
    }
}
