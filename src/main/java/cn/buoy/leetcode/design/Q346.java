package cn.buoy.leetcode.design;

public class Q346 {
    /**
     *
     */
    public class MovingAverage {
        private int[] window;
        //n是有窗口内元素个数; insert是当前要插入的index
        private int n, insert;
        private long sum;

        /**
         * Initialize your data structure here.
         */
        public MovingAverage(int size) {
            window = new int[size];
            insert = 0;
            sum = 0;
        }

        public double next(int val) {
            if (n < window.length) n++;
            //移除
            sum -= window[insert];
            sum += val;
            window[insert] = val;
            //先移动到下次 先删除后插入的位置
            insert = (insert + 1) % window.length;

            return (double) sum / n;
        }
    }
}
