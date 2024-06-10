package cn.buoy.leetcode.array;

public class Q169 {
    // https://www.youtube.com/watch?v=2s7b0zs4Vf4
    // 思路: 任何一個不是多於n/2的數都無法累積超過0(遇到不同數就--的情況下)
    public int majorityElement(int[] nums) {

        int major = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else count--;

        }
        return major;
    }
}
