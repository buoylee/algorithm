package cn.buoy.leetcode.bitmanipulation;

public class Q201 {
    public static void main(String[] args) {
        Q201 q201 = new Q201();
        int i = q201.rangeBitwiseAnd(1, 7);
    }

    /**
     * https://www.youtube.com/watch?v=CY1tnj53L_k
     * 思路: 因爲是[m, n]連續數組, 只要m, n不同, 最後一位一定有一個是0, 當檢查到最後出現 m == n 時, 則把這個相等的數 左移x位(之前去掉的位數)恢復到原長度即可(相等 & 相等 == 相等).
     * <p>
     * ???只要找 最大的 log2N, 例如 10000, & 上 其他数 后续都会变0.
     */
    public int rangeBitwiseAnd(int m, int n) {
        //最小值为0, 直接返回0
        if (m == 0) {
            return 0;
        }
        int moveBit = 0;
        //高位是否相同
        while (m != n) {
            m >>= 1;
            n >>= 1;
            moveBit++;
        }
        //补0
        return m << moveBit;
    }

}
