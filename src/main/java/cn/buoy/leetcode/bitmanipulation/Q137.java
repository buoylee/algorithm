package cn.buoy.leetcode.bitmanipulation;

public class Q137 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=puXcQpwgcD0
     * 思路: 关键要知道 %n 的功能, 能消除 "n倍 num" 的 num.
     * 统计 "所有 num" 在 "同一个 bit" 上的 '1' 的个数, 然后 %3, 即可知道 "该 bit" 是否有 不是 3的倍数个的 1.
     * % 完后, 将 % 后的结果, 填入 result 对应的位置, 当检查完 32bit, 就是答案.
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        // int 有32位
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                // 统计 "所有 num" 在该位上 `1` 的个数, 然后 %3, 只会出现剩0/1, 题目没有2个.
                if (((nums[j] >> i) & 1) == 1)
                    count++;
                count %= 3;
            }
            // 右移几位 取的`1`, 就 左移几位 放入 result.
            if (count != 0)
                result |= count << i;
        }
        return result;
    }
}
