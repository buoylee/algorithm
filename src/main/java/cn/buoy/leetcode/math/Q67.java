package cn.buoy.leetcode.math;

public class Q67 {
    /**
     * 簡單, 視頻, 註釋.
     * https://www.youtube.com/watch?v=3zg1eEAX-1Y
     * 思路: 由低位處理到高位, '2數字相加'再加'進位1', /2 如果 != 0, 則進位, 不要忘記到了頭部如果需要進位, 多append 一個1.
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        // 進位
        int carry = 0;
        while (i >= 0 || j >= 0) {
            // 表示某一位 相加 + 進位 1.
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            // 直接append, 結果需要 reverse.
            sb.append(sum % 2);
            carry = sum / 2;
        }
        // 2個數字都檢查完, 如果還需要進位, 則最後再 append 一個1.
        if (carry != 0)
            sb.append(carry);
        // 翻轉
        return sb.reverse().toString();
    }
}
