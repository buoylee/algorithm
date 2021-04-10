package cn.buoy.leetcode.array;

import java.util.Arrays;

public class Q135 {
    /*
    https://www.youtube.com/watch?v=-XWLumbr4UU
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
            //如果比前边小朋友大,则要比前小朋友的糖还要+1
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i - 1] + 1;
            }
        }

        //然后从后往前检查
        for (int i = n - 1; i > 0; i--) {
            //如果后小朋友比前大, 先后小朋友+1, 再和当前比较大小, 去大值.
            if (ratings[i - 1] > ratings[i]) {
                res[i - 1] = Math.max(res[i] + 1, res[i - 1]);
            }
        }

        int sum = 0;
        for (int r : res) sum += r;

        return sum;
    }
}
