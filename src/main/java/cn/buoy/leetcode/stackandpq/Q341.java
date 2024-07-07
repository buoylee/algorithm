package cn.buoy.leetcode.stackandpq;

import cn.buoy.leetcode.NestedInteger;

import java.util.*;

public class Q341 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=tE15rdrx9BU
     * 思路: 從構造函數, 從頭到尾, 扁平化, 遇到list就拆, 直到是數字, 然後加入到 integerList.
     * 註釋掉的是優化代碼.
     */
    public class NestedIterator implements Iterator<Integer> {
        private List<Integer> integerList = new ArrayList<>();
//        private int index = 0;

        public NestedIterator(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList)
                flatten(nestedInteger);
        }

        private void flatten(NestedInteger nested) {
            if (nested.isInteger())
                integerList.add(nested.getInteger());
            else
                for (NestedInteger nestedFromList : nested.getList())
                    flatten(nestedFromList);
        }

        @Override
        public boolean hasNext() {
//            return index < integerList.size();
            return !integerList.isEmpty();
        }

        @Override
        public Integer next() {
//            return integerList.get(index++);
            return integerList.remove(0);
        }
    }

    /**
     * https://www.youtube.com/watch?v=R2dohSHOWXQ
     * 思路: nestedList 從尾開始放入 stack,
     * hasNext(): 需要確認'第一個'有沒可能是'數', 所以, 當出現list時, 需要一直往內遞歸, 直到找到第個不是list才停止.
     */
    class NestedIterator2 implements Iterator<Integer> {
        Deque<NestedInteger> stack = new ArrayDeque<>();

        public NestedIterator2(List<NestedInteger> nestedList) {
            prepareStack(nestedList);
        }

        @Override
        public Integer next() {
            if (!hasNext())
                return null;
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
            for (int i = nestedList.size() - 1; i >= 0; i--)
                stack.push(nestedList.get(i));
        }
    }
}


