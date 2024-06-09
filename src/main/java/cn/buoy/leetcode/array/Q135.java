package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q135 {
    /*
    https://www.youtube.com/watch?v=-XWLumbr4UU
    思路: 前後2次分配目的在於, 左遍歷, 滿足右鄰居較大的小孩有更多的糖;
    右遍歷, 滿足左鄰居較大的小孩有更多的糖;
    但不可小於(違反)前一次遍歷的最小需求.
     */
    public int candy(int[] ratings) {
        //小朋友个数
        int n = ratings.length;
        //创建返回数组
        int[] res = new int[n];
        //先每人一颗糖
        Arrays.fill(res, 1);
        //从i=1开始和前一个小朋友比
        for (int i = 1; i < n; i++) {
            //如果比前小朋友大,则要比前小朋友的糖+1
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i - 1] + 1;
            }
        }

        //然后从后往前检查
        for (int i = n - 1; i > 0; i--) {
            //如果前小朋友比後大, 用後小朋友糖果+1, 和前小朋友當前糖果比较, 前小朋友當前糖果取大值.
            if (ratings[i - 1] > ratings[i]) {
                res[i - 1] = Math.max(res[i] + 1, res[i - 1]);
            }
        }

        int sum = 0;
        for (int r : res) sum += r;

        return sum;
    }
}
