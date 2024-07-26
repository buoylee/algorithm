package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q135 {
    /**
     * 有点巧妙, 视频
     * https://www.youtube.com/watch?v=-XWLumbr4UU
     * 思路: 从头往尾/从尾往头, 2次分配目的在於, 从头往尾遍歷, 滿足右鄰居較大的小孩有更多的糖; 从尾往头遍歷, 滿足左鄰居較大的小孩有更多的糖;
     * 关键: 但不可小於(違反)前一次遍歷的最小需求.
     */
    public int candy(int[] ratings) {
        // 小朋友个数
        int len = ratings.length;
        // 每个小朋友的 candy 数量 arr
        int[] candyArray = new int[len];
        // 先每人一颗糖
        Arrays.fill(candyArray, 1);
        // 从 i = 1 开始, 和 前一个小朋友 比
        for (int i = 1; i < len; i++)
            // 如果比前小朋友大, 则要比前小朋友的糖+1
            if (ratings[i] > ratings[i - 1])
                candyArray[i] = candyArray[i - 1] + 1;
        // 同理, 从后往前检查
        for (int i = len - 1; i > 0; i--)
            // 如果 前小朋友 比 後 大, 则 前小朋友 candy + 1,
            // 关键: 但是, 为了保证 从前往后分配的 大小前提, 不能少于之前分配的糖量
            if (ratings[i - 1] > ratings[i])
                candyArray[i - 1] = Math.max(candyArray[i] + 1, candyArray[i - 1]);
        int result = 0;
        for (int c : candyArray)
            result += c;
        return result;
    }
}
