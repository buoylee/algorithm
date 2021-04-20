package cn.buoy.leetcode.string;

public class Q157 {
    /**
     * https://www.youtube.com/watch?v=OQUB0d0E2tY
     * 只是扩展了n...
     *
     * @param buf
     * @param n
     * @return
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
