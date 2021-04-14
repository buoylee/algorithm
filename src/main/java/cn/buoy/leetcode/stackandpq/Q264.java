package cn.buoy.leetcode.stackandpq;

public class Q264 {
    public static void main(String[] args) {
        Q264 q264 = new Q264();
        int i = q264.nthUglyNumber(6);
    }

    /**
     * https://www.youtube.com/watch?v=ZG86C_U-vRg
     * 这个就是动态规划
     *
     * @param n
     * @return
     */
    int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;

        for (int i = 1; i < n; i++) {
            // generate ugly number by multiply all the factors
            uglyNumbers[i] = Math.min(uglyNumbers[index2] * 2, Math.min(uglyNumbers[index3] * 3, uglyNumbers[index5] * 5));

            // bump up index for the current minimum ugly number
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
}
