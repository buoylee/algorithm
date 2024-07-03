package cn.buoy.leetcode.math;

public class Q367 {
    /**
     * https://www.youtube.com/watch?v=DPEVsXArw_Q
     * 思路: 典型2分法, 1~num 之間找出 x^2 == num,
     * 有優化寫法, 可不看, right = num / 2, 具體看視頻.
     */
    public boolean isPerfectSquare(int num) {
        long left = 1;
        long right = num; // long right = num / 2; 可不看, 優化點: 由於 當 n > 4 時, num / 2 肯定大於 根號num, 又因爲 當 0 < n <= 4 時, left + 1 < right 還是不滿足, 所以 很巧妙.
        // 典型2分
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (mid * mid == num) return true;
            if (mid * mid > num)
                right = mid;
            else
                left = mid;
        }
        if (left * left == num) return true;
        if (right * right == num) return true;
        return false;
    }


    /**
     * 跳過. 上邊的更好理解
     * https://www.youtube.com/watch?v=ujGK9RQLUco
     * 2分法
     */
    public boolean isPerfectSquare2(int num) {
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
