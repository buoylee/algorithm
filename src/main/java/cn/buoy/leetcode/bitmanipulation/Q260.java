package cn.buoy.leetcode.bitmanipulation;

public class Q260 {
    /**
     * 懂思路就简单, 视频
     * https://www.youtube.com/watch?v=kOMJAZ0t_F4
     * https://www.youtube.com/watch?v=LxcX_NVHep4
     * 思路: 假設a, b這2個不同的數的亦或值爲 n, 说明 n 的2进制表示时, 有 '1' 的位置, 表示, a 和 b 不同的 bit.
     * 然后通過 n & ~n 可以找到a, b這2個數, 從後邊數起第一个在相同位置上不同 value 的值, diff.
     * 利用 不同的这一 bit(diff), 做了2件事, num & diff 時, 1. 保證了分組 nums 時, 肯定把相同的數分到一組; 2. 肯定把a, b 分到不同的組(^后, 肯定有一個是0, 一個是其他數(diff 这一位上是 '1').)
     * 然後不同分組内 亦或 自己的 ele, 分別得到這2個不同的唯一數字.
     * 例:
     * a: 11010
     * ~a: 00101
     * -a = ~(a - 1) = ~(11001) = 00110
     * a & -a, 刚好可以得到, a 最后一bit是 '1'的值, 也就是 10
     */
    public int[] singleNumber(int[] nums) {
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums)
            diff ^= num;
        // Get its last set bit
        // 分离因子(与 其中一个 &, 会得0): 先求出 a ^ b, 做一下操作就求出了 分离因子.
//        diff &= ~(diff - 1);
        diff &= -diff;
        // Pass 2 :
        // num 和 diff & 后的结果作为分组
        int[] result = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            // 關鍵: 用語分組, 1. 保證了分組 nums 時, 肯定把相同的數分到一組; 2. 肯定把a, b 分到不同的組.
            if ((num & diff) == 0)  // the bit is not set
                // 關鍵: 亦或分開了a, b的子數組, 結果就是得到 a 或 b(因爲分組時也保證了相同的數會分到一組).
                result[0] ^= num;
            else // the bit is set
                // 同理, & 完, 剩下就是是另一个解.
                result[1] ^= num;
        }
        return result;
    }
}
