package cn.buoy.leetcode.bitmanipulation;

public class Q268 {
    /**
     * https://www.youtube.com/watch?v=QO-hbElvE3k
     * 还是抑或, 相同value 抑或等于0, 所以便利时 多^一个n, 最后的res 就是缺少的值.
     */
    public int missingNumber(int[] nums) {
        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }
        //是要在nums.len = n 的 范围在0~n(这是n+1个数字) 内找出 nums 少的那个数字, 所以 i 递增^时, 记得要多^一次i.
        return xor ^ i;
    }
}
