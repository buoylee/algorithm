package cn.buoy.leetcode.math;

public class Q263 {
    /**
     * https://www.youtube.com/watch?v=1wpe7yeqZd0
     */
    public boolean isUgly(int num) {
        //被2除过, 4不会遍历了, 完成2, 3, 5 整除.
        for (int i = 2; i < 6 && num > 0; i++)
            while (num % i == 0)
                num /= i;
        //剩1的话, true    .
        return num == 1;
    }
}
