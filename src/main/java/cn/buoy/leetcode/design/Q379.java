package cn.buoy.leetcode.design;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q379 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=QdX9uz53tVE
     * 思路: 只需要提供任意一個有效 num 時候, 可以使用 queue;
     * set 便於 search 需要 release 的 num;
     */
    class PhoneDirectory {
        private Queue<Integer> availableNumbers;
        private Set<Integer> assignedNumbers;
        private int maxNumbers;

        // init
        public PhoneDirectory(int maxNumbers) {
            this.maxNumbers = maxNumbers;
            availableNumbers = new LinkedList<>();
            assignedNumbers = new HashSet<>();
            for (int i = 0; i < maxNumbers; i++)
                availableNumbers.offer(i);
        }

        public int get() {
            if (availableNumbers.isEmpty())
                return -1;
            int number = availableNumbers.poll();
            assignedNumbers.add(number);
            return number;
        }

        public boolean check(int number) {
            return !assignedNumbers.contains(number) && number < maxNumbers; // 單純題目要求 "不能大於" maxNumbers
        }

        public void release(int number) {
            if (assignedNumbers.remove(number))
                availableNumbers.offer(number);
        }
    }

    /**
     * get 返回的是当前index, 每个index 上的value 都会指向 下一个 get的index, 并把当前index 置-1, 然后 把当前位pos移到下一个index即可. 空的时候, 会停留在 最后取完index 的value上.
     * release时, 会将对应的index 的value 指向上一个pos, 然后 将pos移到当前index.
     */
    class PhoneDirectory2 {
        int[] next;
        int pos;

        public PhoneDirectory2(int maxNumbers) {
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
