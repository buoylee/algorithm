package cn.buoy.leetcode.graph;

import java.util.HashSet;

public class Q356 {
    /**
     * https://www.youtube.com/watch?v=i2C2XfBBAWs
     * 主要是如何实现, 思路很多.
     */
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        //有2个目的:
        //找到那个"中点" , 不用除2, 直接求出sum即可, 这里.
        //将每个点 转化 为 方便比较 的形式, 这里是转成"xay".
        for (int[] p : points) {
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);
            String str = p[0] + "a" + p[1];
            set.add(str);
        }
        int sum = max + min;
        for (int[] p : points) {
            //int[] arr = {sum-p[0],p[1]};
            //只要检查 (sum - p[0]) 是否存在即可.
            String str = (sum - p[0]) + "a" + p[1];
            if (!set.contains(str))
                return false;

        }
        return true;
    }
}
