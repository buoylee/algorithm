package cn.buoy.leetcode.graph;

import java.util.HashSet;

public class Q391 {
    /**
     * https://www.youtube.com/watch?v=8JM_dyOu_JY
     * <p>
     * https://leetcode.com/problems/perfect-rectangle/discuss/87181/Really-Easy-Understanding-Solution(O(n)-Java)
     * The right answer must satisfy two conditions:
     * 1.the large rectangle area should be equal to the sum of small rectangles
     * 2.count of all the points should be even, and that of all the four corner points should be one
     * 理解原理就好做.
     */
    public boolean isRectangleCover(int[][] rectangles) {

        if (rectangles.length == 0 || rectangles[0].length == 0) return false;

        int x1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y1 = Integer.MAX_VALUE;
        int y2 = Integer.MIN_VALUE;

        HashSet<String> set = new HashSet<String>();
        int area = 0;

        for (int[] rect : rectangles) {
            //找到 整个正方形 左下角 坐标
            x1 = Math.min(rect[0], x1);
            y1 = Math.min(rect[1], y1);
            //找到 整个正方形 右上角 坐标
            x2 = Math.max(rect[2], x2);
            y2 = Math.max(rect[3], y2);
            //统计小矩形面积
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
            //{x, y, x', y'}对应左下, 右上坐标
            //四角坐标
            String s1 = rect[0] + " " + rect[1];
            String s2 = rect[0] + " " + rect[3];
            String s3 = rect[2] + " " + rect[3];
            String s4 = rect[2] + " " + rect[1];

            //双数(重复)则remove, 单数则保留
            if (!set.add(s1)) set.remove(s1);
            if (!set.add(s2)) set.remove(s2);
            if (!set.add(s3)) set.remove(s3);
            if (!set.add(s4)) set.remove(s4);
        }

        //剩下的 如果不是 有且只有4个角的坐标, false
        if (!set.contains(x1 + " " + y1) || !set.contains(x1 + " " + y2) || !set.contains(x2 + " " + y1) || !set.contains(x2 + " " + y2) || set.size() != 4)
            return false;

        //小矩形面积和 和 左下右上坐标的面积 相同则true.
        return area == (x2 - x1) * (y2 - y1);
    }
}
