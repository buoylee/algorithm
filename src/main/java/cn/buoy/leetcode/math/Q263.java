package cn.buoy.leetcode.math;

public class Q263 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=1wpe7yeqZd0
     * https://www.youtube.com/watch?v=40oHNE_7S_A 更短.
     * 思路: 只能用 [2, 3, 5] 來整除 n, 最後是 1 就true.
     */
    public boolean isUgly(int n) {
        if (n == 0) return false;
        int[] factors = new int[]{2, 3, 5};
        for (int i : factors)
            // 只有能整除才會繼續除, 不然就下一個
            while (n % i == 0)
                n /= i;
        return n == 1;
    }

    public boolean isUgly2(int num) {
        //被2除过, 4不会遍历了, 完成2, 3, 5 整除.
        for (int i = 2; i < 6 && num > 0; i++)
            while (num % i == 0)
                num /= i;
        //剩1的话, true    .
        return num == 1;
    }
}
