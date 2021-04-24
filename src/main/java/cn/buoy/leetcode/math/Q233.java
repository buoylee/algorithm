package cn.buoy.leetcode.math;

public class Q233 {
    public static void main(String[] args) {
        Q233 q233 = new Q233();
        int i = q233.countDigitOne(2147483647);
        System.out.println(i);
    }

    /**
     * https://leetcode-cn.com/problems/number-of-digit-one/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-50/
     * 对每一位是 1 的 情况 统计,
     * <p>
     * 那么此时有三种情况，
     * <p>
     * d == 0，那么千位上 1 的个数就是 xyz * 1000
     * d == 1，那么千位上 1 的个数就是 xyz * 1000 + abc + 1
     * d > 1，那么千位上 1 的个数就是 xyz * 1000 + 1000
     * 为什么呢？
     * 当我们考虑千位是 1 的时候，我们将千位定为 1，也就是 xyz1abc。
     * 对于 xyz 的话，可以取 0,1,2...(xyz-1)，也就是 xyz 种可能。
     * 当 xyz 固定为上边其中的一个数的时候，abc 可以取 0,1,2...999，也就是 1000 种可能。
     * 这样的话，总共就是 xyz*1000 种可能。
     * 注意到，我们前三位只取到了 xyz-1，那么如果取 xyz 呢？
     * 此时就出现了上边的三种情况，取决于 d 的值。
     * d == 1 的时候，千位刚好是 1，此时 abc 可以取的值就是 0 到 abc ，所以多加了 abc + 1。
     * d > 1 的时候，d 如果取 1，那么 abc 就可以取 0 到 999，此时就多加了 1000。
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int count = 0;
        //依次考虑个位、十位、百位...是 1
        //k = 1000, 对应于上边举的例子
        for (int k = 1; k <= n; k *= 10) {
            // xyzdabc
            int abc = n % k;
            int xyzd = n / k;
            int d = xyzd % 10;
            // + 8 优化, 减少 d>1 的判断, 0,1 加了也不会进位, 大于等于2的 则会使 xyz +1.
            int xyz = xyzd / 10;
            count += xyz * k;
            if (d > 1) {
                count += k;
            }
            if (d == 1) {
                count += abc + 1;
            }
            //如果不加这句的话，虽然 k 一直乘以 10，但由于溢出的问题
            //k 本来要大于 n 的时候，却小于了 n 会再次进入循环
            //此时代表最高位是 1 的情况也考虑完成了
            //加这个就会在溢出前一轮 就是xyz 到 0, 不会溢出,不会走到k>n这一步.
            if (xyz == 0) {
                break;
            }
        }
        return count;
    }
}
