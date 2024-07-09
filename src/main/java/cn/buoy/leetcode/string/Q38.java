package cn.buoy.leetcode.string;

public class Q38 {
    /**
     * 簡單, 看懂題, 快速看視頻, 直接看代碼.
     * https://www.youtube.com/watch?v=0EGzSHEbXrQ
     * 思路: count 統計'連續相同 digit' 的 數量, val 記錄是哪個 '連續 digit'.
     */
    public String countAndSay(int n) {
        if (n <= 0) return "-1";
        String result = "1";
        for (int i = 1; i < n; i++)
            result = build(result);
        return result;
    }

    /**
     * 轉爲 "有 n 個 digit1, 有 m 個 digit2..." 的 str
     */
    private String build(String str) {
        StringBuilder builder = new StringBuilder();
        // str 的 index.
        int index = 0;
        // 一次循環就是 一次連續的 digit
        while (index < str.length()) {
            // 取新 digit value, 歸0 count
            char val = str.charAt(index);
            int count = 0;
            while (index < str.length() && // 不超過 str 長度
                    str.charAt(index) == val) { //關鍵: 和第一个 digit 的相同, count++
                index++;
                count++;
            }
            // 發現不同 digit, append 完, 開始下一個 digit
            builder.append(count).append(val);
        }
        return builder.toString();
    }

    // 這個寫的有點不工整
    public String countAndSay2(int n) {
        String str = "1";
        // "1" 已經是 第一個, 所以只需要 構建str n-1 次.
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            char currDigit = str.charAt(0);
            // 當前 digit count
            int currDigitCount = 0;
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                // 相同 digit 就 count++
                if (c == currDigit) {
                    currDigitCount++;
                } else { //
                    sb.append(currDigitCount).append(currDigit);
                    currDigitCount = 1;
                    currDigit = str.charAt(j);
                }
            }
            sb.append(currDigitCount).append(currDigit);
            str = sb.toString();
        }
        return str;
    }


}
