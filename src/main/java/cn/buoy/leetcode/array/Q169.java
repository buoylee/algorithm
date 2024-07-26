package cn.buoy.leetcode.array;

public class Q169 {
    /**
     * 簡單, 知道思路的话. 視頻.
     * https://www.youtube.com/watch?v=2s7b0zs4Vf4
     * 思路: 任何一個不是多於n/2的數都無法累積超過0(遇到不同數就--的情況下)
     * 实现: 相同的 num, count++; 不同就 count--; 如果 count == 0 时, 替换成当前 num, 然后 count++;
     */
    public int majorityElement(int[] nums) {
        // init, 第一个num, 算 count == 1
        int result = nums[0], count = 1;
        // 从 index == 1, 开始比较, 遇到不同的 count --
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) { // 如果 count == 0, 替换成当前 num, 然后 count++
                count++;
                result = nums[i];
            } else if (result == nums[i]) { // num 相同就 count++
                count++;
            } else
                count--;
        }
        return result;
    }
}
