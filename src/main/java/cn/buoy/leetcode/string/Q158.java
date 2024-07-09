package cn.buoy.leetcode.string;

public class Q158 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=w3ke3MQTEJ8
     * https://www.youtube.com/watch?v=E-UIXA6LTIc 短
     * 思路: 先讀buffer, 再read4(). 區別于 157題, 該 read() 會多次延續往下讀 file,
     * 所以, 當 n 無法被 4 整除時(n > 4 時), 是會留下 餘數個 元素,
     * 當下次 read() 時, 會 先返回 上一次遺留的 元素, 再用 read4() 讀下一批 file 內 char.
     */
    // 如果上次 read() 有遺留 char, 下次 buff 當前正要讀的 Index
    private int bufferReadingIndex = 0;
    // buff 中實際有幾個 char
    private int bufferCount = 0;
    private char[] buffer = new char[4];

    public int read(char[] buf, int n) {
        int totalIndex = 0;
        while (totalIndex < n) {
            if (bufferReadingIndex == 0)
                bufferCount = read4(buffer);
            if (bufferCount == 0) break;
            while (totalIndex < n && bufferReadingIndex < bufferCount)
                buf[totalIndex++] = buffer[bufferReadingIndex++];
            // 只要讀完了 bufferCount, 記得 bufferReadingIndex 要置0
            if (bufferReadingIndex >= bufferCount) bufferReadingIndex = 0;
        }
        return totalIndex;
    }

    // dummy
    private int read4(char[] buff) {
        return 0;
    }
}
