package cn.buoy.leetcode.bitmanipulation;

public class Q201 {
    /**
     * 算简单, 视频
     * https://www.youtube.com/watch?v=CY1tnj53L_k
     * 思路: 因爲是[m, n]連續數組, 只要m, n不同, 最後一位一定有一個是0(关键: 不同的位肯定出现在后半部, 这是为什么), 當檢查到最後出現 m == n 時, 則把這個相等的數 左移x位(之前去掉的位數)恢復到原長度即可(相等 & 相等 == 相等).
     * 1. 因为 [m, n] 是 "连续 arr", 所以, 当 m != n 时(arr.len > 1), "最后一 bit" 肯定 是 0/1 交替, 即 result 最后一位是 0.
     * 只要找 最大的 log2N, 例如 10000, & 上 其他数 后续都会变0. 但是这个特征, 代码没有用到.
     */
    public int rangeBitwiseAnd(int m, int n) {
        // 最小值为0, 直接返回0
        if (m == 0) return 0;
        int moveBit = 0;
        // 高位是否相同
        while (m != n) {
            m >>= 1;
            n >>= 1;
            moveBit++;
        }
        // 刚刚去掉的 bit, 用 '0' 补上
        return m << moveBit;
    }

    public static void main(String[] args) {
        Q201 q201 = new Q201();
        int i = q201.rangeBitwiseAnd(1, 7);
    }
}
