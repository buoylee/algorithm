package cn.buoy.leetcode.string;

public class Q168 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=OVuCIyN26nw
     * 思路: /26, char 相對'A'的距離 'A' + --n % 26
     */
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            // 關鍵: 因爲 result 插入的 char 實際上 是相對 'A'的位置 的 char(需要 char 個數 -1)
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }

    public String convertToTitle2(int columnNumber) {
        return columnNumber == 0 ? "" : convertToTitle(--columnNumber / 26) + (char) ('A' + (columnNumber % 26));
    }
}
