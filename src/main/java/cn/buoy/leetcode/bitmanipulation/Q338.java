package cn.buoy.leetcode.bitmanipulation;

public class Q338 {
    /**
     * https://www.youtube.com/watch?v=6VkD1Q9eSpU
     * 巧妙dp
     */
    public int[] countBits(int num) {
        //f[i] 表示 数字为i 是有几个1
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++)
            // 等于 2分之一 + 是否 奇偶数 + 1
            f[i] = f[i >> 1] + (i & 1);
        return f;
    }
}
