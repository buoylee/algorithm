package cn.buoy.leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class Q397 {
    /**
     * https://www.youtube.com/watch?v=HsNz3a5RFSc
     * 思路: 遞歸, 按題目做.
     */
    // 表示 i 的最小步數
    Map<Integer, Integer> dp = new HashMap<>();

    public int integerReplacement(int n) {
        if (n == Integer.MAX_VALUE) return 32;
        if (dp.containsKey(n))
            return dp.get(n);
        if (n == 1)
            return 0;
        int currStep;
        if (n % 2 == 0) {
            currStep = integerReplacement(n / 2) + 1;
        } else {
            currStep = Math.min(integerReplacement(n + 1), integerReplacement(n - 1)) + 1;
        }
        dp.put(n, currStep);
        return currStep;
    }

    /**
     * 迭代方式
     *
     * @param n
     * @return
     */
    public int integerReplacement2(int n) {
        if (n == Integer.MAX_VALUE) return 32; //n = 2^31-1;
        int count = 0;
        while (n > 1) {
            if (n % 2 == 0) n /= 2;
            else {
                if ((n + 1) % 4 == 0 && (n - 1 != 2)) n++;
                else n--;
            }
            count++;
        }
        return count;
    }
}

