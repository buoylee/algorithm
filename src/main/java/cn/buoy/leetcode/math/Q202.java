package cn.buoy.leetcode.math;

import java.util.HashSet;
import java.util.Set;

public class Q202 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=TfTv4G8qrjs
     * 思路: 一直拆, 並保存 digit^2 sum, 一旦出現重複, false; 或者一旦出現 1, true.
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        int squareSum, digit;
        // 一旦出現重複, false
        while (set.add(n)) {
            squareSum = 0;
            while (n > 0) {
                digit = n % 10;
                squareSum += digit * digit;
                n /= 10;
            }
            // 一旦出現 1, true
            if (squareSum == 1)
                return true;
            // 繼續拆數.
            n = squareSum;
        }
        return false;

    }
}
