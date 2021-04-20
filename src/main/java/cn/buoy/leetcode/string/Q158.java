package cn.buoy.leetcode.string;

public class Q158 {

    /**
     * https://www.youtube.com/watch?v=w3ke3MQTEJ8
     * 差别157在于, 如果read 传入的n 在 进过 k次 read4 后, 假如并没有将 read4所有read的都返回, 有剩余时, 下次调用read时, 应该先将上次剩下的读完, 再用read4去取.
     */
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }

    private int read4(char[] buff) {
        return 0;
    }
}
