package cn.buoy.leetcode.array;

public class Q209 {
    /**
     * https://www.youtube.com/watch?v=jp15K7dTCHc
     * 简单! 双指针
     * 因为是正整数, 所以可以用双指针, 偏小时, 移动右指针, 偏大时, 移动左指针.
     */
    public int minSubArrayLen(int target, int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        int low = 0, high = 0, sum = 0, minSize = Integer.MAX_VALUE;

        //右指针移动
        for (high = 0; high < arr.length; high++) {
            sum += arr[high];

            //一旦sum大于等于 target, 开始移动左指针, 直到再次小于target
            while (sum >= target) {
                // 當 low == high 時, 子序列長度爲1;
                minSize = Math.min(minSize, high - low + 1);
                sum -= arr[low++];
            }
        }

        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }
}
