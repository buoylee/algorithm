package cn.buoy.leetcode.string;

public class Q157 {
    /**
     * 簡單, 題目理解的話
     * https://www.youtube.com/watch?v=OQUB0d0E2tY
     * 思路: 從 char 長度爲 x 的源文件, 取 n 個 char, 放到 buf 中.
     * 關鍵在最後一輪判斷 count, n - total 大小, 看註釋.
     */
    public int read(char[] buf, int n) {
        boolean eof = false;      // end of file flag
        int total = 0;            // total bytes have read
        char[] tmp = new char[4]; // temp buffer
        while (!eof && total < n) {
            int count = read4(tmp);
            // check if it's the end of the file
            eof = count < 4;
            // get the actual count
            //最后一轮的时候要判断下
            // 關鍵:
            // 例子: 文件 7, n(總共取) 6, 這時 count > n - total 即 3 > 2;
            // 例子: 文件 5, n(總共取) 6, 這時 count > n - total 即 1 > 2;
            count = Math.min(count, n - total);
            // copy from temp buffer to buf
            for (int i = 0; i < count; i++)
                buf[total++] = tmp[i];
        }
        return total;
    }

    private int read4(char[] tmp) {
        return 0;
    }
}
