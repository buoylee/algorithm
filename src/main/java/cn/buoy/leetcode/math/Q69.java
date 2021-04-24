package cn.buoy.leetcode.math;

public class Q69 {
    /**
     * https://www.youtube.com/watch?v=JrBlp8xWqSg
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            //避免int越界
            int mid = left + (right - left) / 2;
            //也是越界, mid * mid > x
            //关键在于, 如果不是完全平方数, 最后的mid 可能会小, 也可能大, 需要选小的返回.
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }
}
