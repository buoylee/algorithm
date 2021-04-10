package cn.buoy.leetcode.array;

public class Q330 {
    public static void main(String[] args) {

        int[] nums = {1,};
        Q330 q330 = new Q330();
        int i = q330.minPatches(nums, 20);

    }

    /*
    https://www.youtube.com/watch?v=f0zzWwclhYQ

     */
    public int minPatches(int[] nums, int n) {
        long missing = 1;
        int patches = 0, i = 0;

        while (missing <= n) {
            if (i < nums.length && nums[i] <= missing) {
                missing += nums[i++];
            } else {
                //如果超过了组成完整序列的必要数字(missing)大小时, 这时 missing < nums[i], 无法满足组成完整序列, 加上该值.
                //这里产生数字,就可以组成完成数列.
                //1, 2, 4, 8, 16...
                missing += missing;
                patches++;
            }
        }
        return patches;
    }
}
