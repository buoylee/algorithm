package cn.buoy.leetcode.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q296 {
    /**
     * https://www.youtube.com/watch?v=Klf0EVLsKqs
     * 用到一个数学原理, 距离之和最小的点, 是他们的中位数, 偶数个数不影响, 任选一个.
     * 化简后 其实就是 分别求 x集合 和 y集合 的到相同点距离最小之和.
     */
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //纵坐标集合
        List<Integer> I = new ArrayList<>(m);
        //横坐标集合
        List<Integer> J = new ArrayList<>(n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                    J.add(j);
                }
            }
        }

        return getMin(I) + getMin(J);
    }

    //这个比较巧妙, 忽略了 中位数, 反正就是在 之间 取一个点, 这个点都是最外2点直接的距离 之和.
    //视频中的方式是, 分别求出 (x, y)实际的 中位数 (min+large)/2 即可(偶数个中位数结果是一样的, 只不多另一边多走相同距离),然后再次遍历所有矩阵的点与该点的 (x差值 + y差值 之和) 的和 即可.
    private int getMin(List<Integer> list) {
        int ret = 0;

        Collections.sort(list);

        int i = 0;
        int j = list.size() - 1;
        //奇数时, 不用算, 剩1点 差也是0.
        while (i < j) {
            ret += list.get(j--) - list.get(i++);
        }

        return ret;
    }
}
