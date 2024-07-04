package cn.buoy.leetcode.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q296 {
    /**
     * 懂了原理就簡單, 直接看題目, 註釋比較快. dfs 可能過不了?
     * https://www.youtube.com/watch?v=Klf0EVLsKqs
     * https://www.youtube.com/watch?v=KfH51O3l2EM 原始2分思路(求中點 再統計中點座標 和 所有 '1'的距離), 實際整個過程 可以簡化爲 就只是求2點距離. 看下邊註釋.
     * 思路: 用到一个数学原理, 距离之和最小的点, 是他们的中位数, 偶数个数不影响, 任选一个.
     * 實際轉爲, 分别求 x集合 和 y集合 的到'相同点'距离最小之和.
     */
    public int minTotalDistance(int[][] grid) {
        // 這個是y
        int rowLen = grid.length;
        // 這個是x
        int colLen = grid[0].length;
        //纵坐标集合
        List<Integer> I = new ArrayList<>(rowLen);
        //横坐标集合
        List<Integer> J = new ArrayList<>(colLen);
        // 所有 '1' 的座標分別加入 x/y集合
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                    J.add(j);
                }
            }
        }
        return getMin(I) + getMin(J);
    }

    //这个比较巧妙, 忽略了 中位数, 反正就是在 之间 取一个点, 这个点都是最外2点直接的距离 之和.
    //视频中的方式是, 分别求出 (x, y)实际的 中位数 (min+large)/2 即可(偶数个中位数结果是一样的, 只不過另一边多走相同距离),然后再次遍历所有矩阵的点与该点的 (x差值 + y差值 之和) 的和 即可.
    private int getMin(List<Integer> list) {
        int ret = 0;
        Collections.sort(list);
        int start = 0;
        int end = list.size() - 1;
        //奇数时, 不用算, 剩1点 差也是0.
        while (start < end)
            // 關鍵: 這樣纔是所有點的中點.
            ret += list.get(end--) - list.get(start++);
        return ret;
    }
}
