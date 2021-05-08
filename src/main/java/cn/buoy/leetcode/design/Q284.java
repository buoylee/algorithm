package cn.buoy.leetcode.design;

import java.util.Iterator;

public class Q284 {
    /**
     * https://www.youtube.com/watch?v=evNDFy6Gtg0
     */
    class PeekingIterator implements Iterator<Integer> {
        private Integer next = null;
        private Iterator<Integer> iter;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            //初始化时, 先 取出一次next 放入 this.next
            iter = iterator;
            if (iter.hasNext())
                next = iter.next();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        //返回this.next, 再将 this.next 指向 iter.next().
        @Override
        public Integer next() {
            Integer res = next;
            next = iter.hasNext() ? iter.next() : null;
            return res;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
