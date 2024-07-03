package cn.buoy.leetcode.math;

public class Q8 {
    /**
     * 簡單, 視頻, 注意代碼
     * https://www.youtube.com/watch?v=FcT5-yrKtyY
     * 思路: 先去空, 然後找合法的數字(連續的 例如: +/-234728 這樣的str), 還要判斷越界.
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int index = 0;
        // 去空格
        while (index < str.length() && str.charAt(index) == ' ')
            index++;
        // 去完空格, 又已經到達 str 結尾, 就是空str, 返回 0.
        if (index == str.length())
            return 0;
        // 只把最後一個 +/- 作爲 sign, 也是作爲答案的數的開頭(符合 +/-8283712 這樣的連貫才是數)
        int sign = 1;
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        //沒有數 或者 只有 +/-, 當0返回.
        int res = 0;
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            // 只要不是數字後續都不要了.
            if (digit < 0 || digit > 9) break;
            int newRes = res * 10 + digit;
            // 關鍵: 檢查越界, 把新數還原, 判斷是否與上一步相同, 不同就是越界. 根據sign來 返回最大/最小int.
            if (newRes / 10 != res)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = newRes;
            index++;
        }
        return res * sign;
    }
}
