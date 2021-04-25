package cn.buoy.leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class Q397 {
    /**
     * https://www.youtube.com/watch?v=HsNz3a5RFSc
     */
    Map<Long, Integer> dp;

    public int integerReplacement(int n) {
        dp = new HashMap();
        return helper(n);
    }

    private int helper(long n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        if (n == 1) {
            return 0;
        }
        if (n <= 0) {
            return Integer.MAX_VALUE;
        }
        int minS;
        //考虑所有可能步骤
        //都是本步 + 下边所有步
        if (n % 2 == 0) {
            minS = helper(n / 2) + 1;
        } else {
            //+ 比较小的那种
            minS = Math.min(helper(n + 1), helper(n - 1)) + 1;
        }
        dp.put(n, minS);
        return minS;
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

