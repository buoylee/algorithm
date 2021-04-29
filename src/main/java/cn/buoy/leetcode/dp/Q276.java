package cn.buoy.leetcode.dp;

public class Q276 {
    /**
     *https://www.youtube.com/watch?v=XL1icaNQCa4
     * 思路: dp[n] 是由 index i 色 ==  index i-1 色 , 和 i 色 !=  index i-1 色 的可能 的和.
     * dp[i] = dp_same[i-1] + dp_diff[i-1]
     * dp_same[i], 意义是 i == i-1 的颜色, dp_diff[i], 意义是 i =! i-1 的颜色.
     * 如果 dp_same[i], 则 dp_diff[i-1], 即 i-2 != i-1. dp_same[i] == dp_diff[i-1]
     * 如果 dp_diff[i], 则 i-2 可以是任何色(可以和 i-1 相同或不同), 即 dp_diff[i-1]*(k-1) + dp_same[i-1]*(k-1).
     */
    public int numWays2(int n, int k) {
        if(n == 0) return 0;
        else if(n == 1) return k;
        int diffColorCounts = k*(k-1);
        int sameColorCounts = k;
        for(int i=2; i<n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k-1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }


    public int numWays(int n, int k) {
        // if there are no posts, there are no ways to paint them
        if (n == 0) return 0;

        // if there is only one post, there are k ways to paint it
        if (n == 1) return k;

        // if there are only two posts, you can't make a triplet, so you
        // are free to paint however you want.
        // first post, k options. second post, k options
        if (n == 2) return k*k;

        int table[] = new int[n+1];
        table[0] = 0;
        table[1] = k;
        table[2] = k*k;
        //table[3] = n * 1 * (n-1) + n * (n -1) * n
        //table[4] = n * 1 * (n-1) * n + n * (n -1) * n *

        for (int i = 3; i <= n; i++) {
            // the recursive formula that we derived
            //[1,1,...], n * 1 * (n-1)
            //[1,2,...], n * (n -1) * n

            //
            table[i] = (table[i-1] + table[i-2]) * (k-1);
        }
        return table[n];
    }


}
