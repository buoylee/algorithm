package cn.buoy.leetcode.math;

public class Q66 {
    /**
     * 簡單, 視頻, 註釋; 類似 369.
     * https://www.youtube.com/watch?v=Bo2YrLkD1es
     * 尾到頭來處理.
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        //尾到头 ++
        for (int i = len - 1; i >= 0; i--) {
            // 小於9的2種情況:
            // 1. 最後一位是 非9, 直接+1 就退出了.
            // 2. 後邊有9, 進位, 所以+1.
            if (digits[i] <= 8) {
                digits[i]++;
                return digits;
            }
            // 等於9 就+1 變0(進位).
            digits[i] = 0;
        }
        //當檢查完整個 digits 還沒退出, 來到這裏, 表示arr[0]也需要進位了.
        int[] newNumber = new int[len + 1];
        newNumber[0] = 1;
        return newNumber;
    }
}
