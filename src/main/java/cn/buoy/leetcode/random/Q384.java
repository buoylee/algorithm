package cn.buoy.leetcode.random;

import java.util.Arrays;
import java.util.Random;

public class Q384 {
    /**
     * 簡單, 懂思路的話, 視頻, 代碼.
     * https://www.youtube.com/watch?v=VO0mf19dBWU 短
     * 思路: rdm 一個 index 與 '末尾未被交換過的index' 交換元素位置, 一直到頭.
     */
    private int[] nums;
    private Random rand;

    public int[] shuffle() {
        int[] clone = nums.clone();
        // 關鍵: curr 表示 隨機出來的數 後續存放的位置. curr 後邊的index都是完成了的元素.
        for (int curr = nums.length - 1; curr >= 0; curr--) {
            // 關鍵: rdm取 0~curr
            int randNum = rand.nextInt(curr + 1);
            swap(clone, randNum, curr);
        }
        return clone;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * https://www.bilibili.com/video/BV1sD4y1d7bU?from=search&seid=7719946238721047820
     */
//    int[] nums;
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
    public int[] shuffle2() {
        int[] res = new int[nums.length];
        res = nums.clone();

        for (int i = 0; i < nums.length; i++) {
            //0~i + random[n-i),
            //base + 后边段随机.
            int rand = i + (int) (Math.random() * (nums.length - i));
            swap2(res, i, rand);
        }
        return res;
    }

    public void swap2(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4};
        Q384 q384 = new Q384(ints);
        int[] shuffle = q384.shuffle();
    }
}
