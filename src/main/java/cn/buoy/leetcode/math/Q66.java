package cn.buoy.leetcode.math;

public class Q66 {
    /**
     * https://www.youtube.com/watch?v=Bo2YrLkD1es
     */
    public int[] plusOne(int[] digits) {

        int n = digits.length;
        //尾到头 ++
        for (int i = n - 1; i >= 0; i--) {
            //小于9 直接 本位+
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            //一直置0
            digits[i] = 0;
        }
        //没结束, 说明一直是 9999..., 直接 进位1, 后边 全0.
        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;

        return newNumber;
    }
}
