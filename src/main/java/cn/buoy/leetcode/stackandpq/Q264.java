package cn.buoy.leetcode.stackandpq;

public class Q264 {
    /**
     * 看視頻, 結合代碼看 要複習
     * https://www.youtube.com/watch?v=ZG86C_U-vRg
     * https://www.youtube.com/watch?v=LhSmDHQUqh8 這個好點
     * <p>
     * https://www.youtube.com/watch?v=LqU1XR9Khdg 超清楚
     * <p>
     * 这个就是动态规划, 1也算丑數.
     * 思路: 從最小的可能的丑數找起, dp[i] 通過 min(dp[index2/3/5] * 2/3/5), 只有min那一個*數, 才需要index++, 因爲當前已是最小(已排到丑數最後一位), 如果有 * 2/3/5 後 相同, index都要++
     */
    int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        // index == 0 也是
        uglyNumbers[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;

        for (int i = 1; i < n; i++) {
            // generate ugly number by multiply all the factors
            // ugly0 = 1

            // index2/3/4 = 0 0 0
            // ugly1 = 2 3 5; 取 2
            // index2/3/4 = 1 0 0
            // ugly2 = 4 3 5; 取 3
            // index2/3/4 = 1 1 0
            // ugly3 = 4 9 5; 取 4
            // index2/3/4 = 1 1 1
            // ugly4 = 8 9 5; 取 5

            // index2/3/5的值 都是從 0 開始.
            // uglyNumbers[0]的值 從 1開始.
            uglyNumbers[i] = Math.min(uglyNumbers[index2] * 2, Math.min(uglyNumbers[index3] * 3, uglyNumbers[index5] * 5));

            // bump up index for the current minimum ugly number
            // 跳過 與 剛剛 找到的min 相同的 index
            if (uglyNumbers[i] == uglyNumbers[index2] * 2) index2++;
            if (uglyNumbers[i] == uglyNumbers[index3] * 3) index3++;
            if (uglyNumbers[i] == uglyNumbers[index5] * 5) index5++;
        }

        return uglyNumbers[n - 1];
    }

    /**
     * 最小堆
     * https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/
     */
    // TODO: 2021/4/12
    public static void main(String[] args) {
        Q264 q264 = new Q264();
        int i = q264.nthUglyNumber(6);
    }
}
