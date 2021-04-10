package cn.buoy.leetcode.dfsandbfs;

import java.util.ArrayList;
import java.util.List;

public class Q364 {
    /*
    https://www.youtube.com/watch?v=GgSeKo7fRrE
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger())
                    //这里是今层 + 以往所有层(都是只加了1次)
                    unweighted += ni.getInteger();
                else
                    nextLevel.addAll(ni.getList());
            }
            //关键在这累加, 第一次, weighted = 1层; 第2次, weighted = 1层 + (2层 + 1层); 第3次, weighted = 1层 + (2层 + 1层) + (3层 + 2层 + 1层);
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }
}
