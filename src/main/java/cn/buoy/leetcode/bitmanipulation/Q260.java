package cn.buoy.leetcode.bitmanipulation;

public class Q260 {
    /**
     * https://www.youtube.com/watch?v=kOMJAZ0t_F4
     * https://www.youtube.com/watch?v=LxcX_NVHep4
     * <p>
     * 思路: 假設a, b這2個不同的數的亦或值爲n, 通過 n & ~n 可以找到a, b這2個數從後邊數起第一個1的值 diff(表示a和b在這一位是不同的).
     * 利用這個 diff, 做了2件事, num & diff時, 1. 保證了分組 nums 時, 肯定把相同的數分到一組; 2. 肯定把a, b 分到不同的組(肯定有一個是0, 一個是其他數.)
     * 然後通過亦或不同分組的子數組, 分別得到這2個不同的唯一數字.
     */
    public int[] singleNumber(int[] nums) {
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
            // 關鍵: 用語分組, 1. 保證了分組 nums 時, 肯定把相同的數分到一組; 2. 肯定把a, b 分到不同的組.
            if ((num & diff) == 0) { // the bit is not set
                // 關鍵: 亦或分開了a, b的子數組, 結果就是得到 a 或 b(因爲分組時也保證了相同的數會分到一組).
                rets[0] ^= num;
            } else { // the bit is set
                //另一解 与 另一組偶数个元素 &完, 剩下是另一个解.
                rets[1] ^= num;
            }
        }
        return rets;
    }
}
