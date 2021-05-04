package cn.buoy.leetcode.bitmanipulation;

public class Q201 {
    public static void main(String[] args) {
        Q201 q201 = new Q201();
        int i = q201.rangeBitwiseAnd(1, 7);
    }

    /**
     * https://www.youtube.com/watch?v=CY1tnj53L_k
     * 只要找 最大的 log2N, 例如 10000, & 上 其他数 后续都会变0.
     * 如果结尾是0.
     * 从后检查, 如果>> 后 2数相等, 不用比,
     */
    public int rangeBitwiseAnd(int m, int n) {
        //最小值为0, 直接返回0
        if (m == 0) {
            return 0;
        }
        int moveFactor = 1;
        //高位是否相同
        while (m != n) {
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        //补0
        return m * moveFactor;
    }

}
