package cn.buoy.leetcode.random;

import java.util.Random;

public class Q398 {
    /**
     * https://www.youtube.com/watch?v=SnlxL5_LF7g
     * 还算简单, 记住, 不是 拿到了就返回, 是要遍历到最后, 每次都选当前元素概率为1/n, 保留上次的元素概率为2/3, 不要搞错.
     * 一直保留 第1个数的 概率是 1* 1/2 * 2/3 * n-1/n, 结果 第一个数的概率 还是为 1/n. 推导成立.
     */
    private int[] nums;

    public Q398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Random r = new Random();
        int n = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == target) {
                //计算同一个target的个数
                n++;
                //我们以同一个数字的频数1/n的概率选出其中一个索引
                if (r.nextInt(n) == 0) index = i;
            }
        return index;
    }
}
