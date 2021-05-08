package cn.buoy.leetcode.design;

public class Q379 {
    /**
     * 画图! 就好理解了. 不难.
     * get 返回的是当前index, 每个index 上的value 都会指向 下一个 get的index, 并把当前index 置-1, 然后 把当前位pos移到下一个index即可. 空的时候, 会停留在 最后取完index 的value上.
     * release时, 会将对应的index 的value 指向上一个pos, 然后 将pos移到当前index.
     */
    class PhoneDirectory {

        /**
         * Initialize your data structure here
         *
         * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
         */
        int[] next;
        int pos;

        public PhoneDirectory(int maxNumbers) {
            next = new int[maxNumbers];
            for (int i = 0; i < maxNumbers; ++i) {
                next[i] = (i + 1) % maxNumbers;
            }
            pos = 0;
        }

        /**
         * Provide a number which is not assigned to anyone.
         *
         * @return - Return an available number. Return -1 if none is available.
         */
        public int get() {
            if (next[pos] == -1) return -1;
            int ret = pos;
            pos = next[pos];
            next[ret] = -1;
            return ret;
        }

        /**
         * Check if a number is available or not.
         */
        public boolean check(int number) {
            return next[number] != -1;
        }

        /**
         * Recycle or release a number.
         */
        public void release(int number) {
            if (next[number] != -1) return;
            next[number] = pos;
            pos = number;
        }
    }
}
