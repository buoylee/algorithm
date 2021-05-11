package cn.buoy.leetcode.array;

public class Q238 {
    /**
     * https://www.youtube.com/watch?v=rpQhKorJRd8
     * 思路: res[i] 等于 i的`左边累乘`*`右边累乘`.
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        //第一步:累乘 i左边 元素, 并放入res
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        //开始从尾开始 右边累乘,
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            //乘上 第一步的左边累乘 就是res[i]
            right *= nums[i];
        }
        return res;
    }
}
