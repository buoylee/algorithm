package cn.buoy.leetcode.design;

import java.util.Iterator;

public class Q284 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=evNDFy6Gtg0
     * https://www.youtube.com/watch?v=5aATZPMKJ-I 短
     * 思路: 提前一步取一次 next 來當 curr, 作爲 peek() 的返回; next() 返回 curr, 馬上取一次 next 填補 curr.
     */
    class PeekingIterator implements Iterator<Integer> {
        private Integer curr = null;
        private Iterator<Integer> iter;

        public PeekingIterator(Iterator<Integer> iterator) {
            // 關鍵: 初始化时, 先取出一次 next 放入 curr
            iter = iterator;
            if (iter.hasNext())
                curr = iter.next();
        }

        public Integer peek() {
            return curr;
        }

        @Override
        public Integer next() {
            // 關鍵: 返回 curr 後, 馬上取一次 next, 沒有就賦值 null.
            Integer result = curr;
            curr = iter.hasNext() ? iter.next() : null;
            return result;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }
    }
}
