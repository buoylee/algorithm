package cn.buoy.leetcode.bitmanipulation;

public class Q260 {
    /**
     * https://www.youtube.com/watch?v=kOMJAZ0t_F4
     */
    public int[] singleNumber(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
//        diff &= -diff;
        //分离因子(与 其中一个& 会得0): 先求出 a ^ b, 做一下操作就求出了 分离因子.
        diff &= ~(diff - 1);

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) { // the bit is not set
                //这里直接找到 & diff == 0 的, 就是其一解
                rets[0] ^= num;
            } else { // the bit is set
                //另一解 与 其他偶数个元素 &完, 剩下是另一个解.
                rets[1] ^= num;
            }
        }
        return rets;
    }
}
