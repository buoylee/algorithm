package cn.buoy.leetcode.array;

public class Q209 {
    /**
     * https://www.youtube.com/watch?v=jp15K7dTCHc
     * 简单! 双指针
     * 因为是正整数, 所以可以用双指针, 偏小时, 移动右指针, 偏大时, 移动左指针.
     */
    public int minSubArrayLen(int s, int[] a) {
        if (a == null || a.length == 0)
            return 0;

        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;

        //右指针移动
        for (j = 0; j < a.length; j++) {
            sum += a[j];

            //一旦sum大于等于 target, 开始移动左指针, 直到再次小于target
            while (sum >= s) {
                min = Math.min(min, j - i + 1);
                sum -= a[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
