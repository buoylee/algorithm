package cn.buoy.leetcode.stackandpq;

import cn.buoy.leetcode.NestedInteger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Q341 {
    /**
     * https://www.youtube.com/watch?v=tE15rdrx9BU
     * <p>
     * https://www.youtube.com/watch?v=R2dohSHOWXQ
     */
    class NestedIterator implements Iterator<Integer> {
        Deque<NestedInteger> stack = new ArrayDeque<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            prepareStack(nestedList);
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                return null;
            }
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            //一直拆, 拆到 第一个 弹出的 是数字 为止
            while (!stack.isEmpty() && !stack.peek().isInteger()) {
                List<NestedInteger> list = stack.pop().getList();
                prepareStack(list);
            }
            return !stack.isEmpty();
        }

        //从尾到头, 压入栈中
        private void prepareStack(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }
    }
}


