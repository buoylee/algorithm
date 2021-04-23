package cn.buoy.leetcode.math;

public class Q367 {
    /**
     * https://www.youtube.com/watch?v=ujGK9RQLUco
     * 2分法
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //商, 余数.
            int res = num / mid, tail = num % mid;
            //只有 商 和 除数 相等 , 且 余为0 时, 为完全平数.
            if (tail == 0 && res == mid)
                return true;
            else if (res < mid) {
                //要的是 res, mid 相等, 就要变小mid, 增大res.
                right = mid - 1;
            } else
                left = mid + 1;
        }
        return false;
    }
}
