package cn.buoy.leetcode.bitmanipulation;

public class Q137 {
    /**
     * https://www.youtube.com/watch?v=puXcQpwgcD0
     * 直接看視頻, 然後代碼就懂了, 算簡單. 思路: 使 所有元素 某位上 有n(题目要求 除答案外 重复元素的重复次数)的倍数的`1` 转化为0, 这 剩下的就是 答案.
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        //int 有32位
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                //统计`1`个数,然后%3, 只会出现剩1, 题目没有2个.
                if (((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }
            //左移几位取的`1`, 就 移回 几位 放回去.
            if (sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }
}
