package cn.buoy.leetcode.array;

public class Q376 {
    /**
     * https://www.bilibili.com/video/BV1cZ4y1A7Pn?from=search&seid=8232337475929480634
     * 画图! 还算比较好想!
     */
    public int wiggleMaxLength(int[] nums) {

        if (nums.length == 0) return 0;

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        //无论是up down 都会记1.
        up[0] = 1;
        down[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            //如果递增, 就要拿 down[i - 1] + 1
            //因为是递增, 所以 up[0] 只能维持一样.
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                //同理
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }

        //选 down, up末尾index 大的value即可.
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}
