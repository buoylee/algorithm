package cn.buoy.leetcode.array;

public class Q376 {
    /**
     * https://www.bilibili.com/video/BV1cZ4y1A7Pn?from=search&seid=8232337475929480634
     * 简单, 直接看
     */
    public int wiggleMaxLength(int[] nums) {

        if (nums.length == 0) return 0;

        //这里的dpUp代表的是到达该index时, 前一次是递增, 的最长的seq.size
        int[] dpUp = new int[nums.length];
        //这里的dpDown代表的是到达该index时, 前一次是递减, 的最长的seq.size
        int[] dpDown = new int[nums.length];

        //无论up/down 初始记1.
        dpUp[0] = 1;
        dpDown[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            //如果递增, 就要拿 dpDown[i - 1] + 1
            //因为是递增, 所以 dpUp[0] 只能维持一样.
            if (nums[i] > nums[i - 1]) {
                dpUp[i] = dpDown[i - 1] + 1;
                dpDown[i] = dpDown[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                //同理
                dpDown[i] = dpUp[i - 1] + 1;
                dpUp[i] = dpUp[i - 1];
            } else {
                //value相等就跳过(直接复制)
                dpDown[i] = dpDown[i - 1];
                dpUp[i] = dpUp[i - 1];
            }
        }

        //最终取较大 seq size 即可.
        return Math.max(dpDown[nums.length - 1], dpUp[nums.length - 1]);
    }
}
