package cn.buoy.leetcode.math;

public class Q43 {
    /**
     * https://www.youtube.com/watch?v=kY7SI207GAk
     * https://www.youtube.com/watch?v=q3vpdwWR0ag 這個講的非常具體
     * 就是基礎乘法, 關鍵是 計算過程中數字的位置.
     * int high = i + j;
     * int low = i + j + 1;
     */
    public String multiply(String num1, String num2) {
        int num1Len = num1.length();
        int num2Len = num2.length();
        int[] digits = new int[num1Len + num2Len];
        for (int i = num1Len - 1; i >= 0; i--) {
            for (int j = num2Len - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                //關鍵5行
                int high = i + j;
                int low = i + j + 1;
                //high 不会有数, 不用加? 具體可能第2個視頻.
                int sum = mul + digits[low];
                digits[high] += sum / 10;
                digits[low] = (sum) % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : digits)
            // 跳過 第一個非0的數字 前邊的所有0.
            if (!(sb.length() == 0 && digit == 0))
                sb.append(digit);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
