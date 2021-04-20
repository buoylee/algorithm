package cn.buoy.leetcode.string;

public class Q168 {
    /**
     * https://www.youtube.com/watch?v=OVuCIyN26nw
     */
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while (n > 0) {
            //这里注意, 相差 其实是 -1.
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
