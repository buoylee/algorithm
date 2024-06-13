package cn.buoy.leetcode.array;

public class Q330 {
    public static void main(String[] args) {

        int[] nums = {1,};
        Q330 q330 = new Q330();
        int i = q330.minPatches(nums, 20);

    }

    /**
     * 需要理解
     * https://www.youtube.com/watch?v=f0zzWwclhYQ
     * 思路: 因为要完全覆盖[1, n], 所以我们可以从1开始检查一路到n, 保证每一次preSum[i]都包含所有[1, preSum[i]]
     *
     * 有3种情况:
     * 刚好满足: [1, 2, 4, 8],     [1,2), [1,4), [1,8), [1,16)
     * 偏小:    [1, 2, 2, 4...], [1,2), [1,4), [1,6), [1,10)(这里开始缺少7, 当前新加入的数不大于7, 都能用前边的数补足, 所有满足条件可以继续检查)
     * 偏大:    [1, 2, 2, 8...], [1,2), [1,4), [1,6), [1,X)(这里开始缺少7, 在他之前都可以保证[1, 6], 但是下一个数是8, 如何都无法凑到7, 所以缺失.)
     */
    public int minPatches(int[] nums, int n) {
        //理解为当前需要满足的缺失的sum, 要满足这个sum, 下一个value可以在[1~sum], 为什么取sum值是最短解(到达预设的n), 因为可以覆盖更后边的范围.
        //todo 是如何保证之前的sum包含所有数? 只能用dp思想解释?
        long missing = 1;
        int patches = 0, numsIndex = 0;

        while (missing <= n) {
            // 关键就在 nums[numsIndex] <= missing 和 missing += nums[numsIndex++] 和 missing += missing的理解
            if (numsIndex < nums.length && nums[numsIndex] <= missing) {
                missing += nums[numsIndex++];
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
