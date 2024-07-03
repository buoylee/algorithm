package cn.buoy.leetcode.math;

public class Q306 {
    /**
     * 簡單, 有些邊界問題不太好想
     * https://www.youtube.com/watch?v=LziJZT2uRwc
     * https://www.youtube.com/watch?v=Yq_dyoVUUmU 短
     * 思路: 雙循環 確定第一和第二個數字長度, 一旦確定, 後續數字長度也確定了, 只需要一直用最後2數相加就好.
     */
    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        // i作爲結尾 在 substr 時, 不包含i.
        // 關鍵: i, j 表示 取幾位.
        for (int i = 1; i <= len / 2; ++i)
            // 因为1,2 位的和 的位数, 肯定大于等于 1,2位较大哪一数长度.
            // 例: 999108
            // i = 1, j = 2, max == 2; 6 - 1 - 2 =3
            for (int j = 1; Math.max(j, i) <= len - i - j; ++j)
                if (isValid(i, j, num)) return true;
        return false;
    }

    private boolean isValid(int i, int j, String num) {
        // 細節: 當取'超過2位數字'時, 首位不能是 0.
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        Long sum = 0L;
        Long numFirst = Long.parseLong(num.substring(0, i));
        Long numSecond = Long.parseLong(num.substring(i, i + j));
        // 關鍵: 當第一和第二個數數字確定後, 後續每個number長度也就決定了.
        //start 是sum 数字 的第一位digit; 也是上一輪的 second 和 sum.
        // 細節: 第一次進入 isValid() 前, 已經判斷一次 Math.max(j, i) <= len - i - j. 且 start += String.valueOf(sum).length()
        for (int start = i + j; start != num.length(); start += String.valueOf(sum).length()) {
            sum = numFirst + numSecond;
            //second 作为下一轮的 first
            numFirst = numSecond;
            //sum 作为下一轮的 second
            numSecond = sum;
            //对比 是否以sum爲前綴即可.
            if (!num.startsWith(String.valueOf(sum), start))
                return false;
        }
        return true;
    }
}
