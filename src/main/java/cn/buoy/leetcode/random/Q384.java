package cn.buoy.leetcode.random;

import java.util.Arrays;

public class Q384 {

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4};
        Q384 q384 = new Q384(ints);
        int[] shuffle = q384.shuffle();
    }

    /**
     * https://www.bilibili.com/video/BV1sD4y1d7bU?from=search&seid=7719946238721047820
     * 思路 和排列组合一样, n选1, (n-1)选1...
     */
    int[] nums;

    public Q384(int[] nums) {
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int[] res = new int[nums.length];
        res = nums.clone();

        for (int i = 0; i < nums.length; i++) {
            //0~i + random[n-i),
            //base + 后边段随机.
            int rand = i + (int) (Math.random() * (nums.length - i));
            swap(res, i, rand);
        }
        return res;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
