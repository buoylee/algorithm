package cn.buoy.leetcode.array;

public class Q55 {
    /*
    https://www.youtube.com/watch?v=yHU3nrww_ZA
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int last = n - 1;
        //倒数第二位开始检查能否到达最后 n-1 位
        for (int i = n - 2; i >= 0; i--) {
            //如果当前index + 当前index最远可跳步数, 可到达 n-1, 则将检查点设置为当前index.
            if (i + nums[i] >= last)
                last = i;
        }
        //如果能到达index = 1, 说明有解.
        return last <= 0;
    }
}
