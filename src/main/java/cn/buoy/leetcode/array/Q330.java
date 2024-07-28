package cn.buoy.leetcode.array;

public class Q330 {
    /**
     * 需要理解, 只是一種規律.
     * https://www.youtube.com/watch?v=f0zzWwclhYQ
     * 思路: 因为要完全覆盖[1, n], 所以我们可以从1开始, 計算 preSum, 保证每一次 preSum[i] > nums[i], 才能組成完全覆蓋[1, preSum[i]]
     * <p>
     * 有3种情况:
     * 刚好满足: [1, 2, 4, 8],     [1,2), [1,4), [1,8), [1,16)
     * 偏小:    [1, 2, 2, 4...], [1,2), [1,4), [1,6), [1,10) (仍然 完全覆蓋[1, 10], 只是不是 "最小 len" 的 arr)
     * 偏大:    [1, 2, 2, 7...], [1,2), [1,4), [1,6), {缺少6,直接到7了} (这里开始缺少6, 在他之前都可以保证[1, 6], 但是下一个数是7, 如何都无法凑到6, 所以缺失.)
     */
    public int minPatches(int[] nums, int n) {
        // 理解为当前需要满足的缺失的 num, 要满足这个 num, 下一个 num 可以在[1~num], 为什么取 num 值是最短解(到达预设的n), 因为可以覆盖更后边的范围.
        // 是如何保证之前的sum包含所有数? 只能用dp思想解释? 就是一種規律.
        // 從1開始檢查.
        long missing = 1;
        // 需要補幾個數
        int patches = 0;
        // nums 遍歷到的 index
        int arrIdx = 0;
        while (missing <= n) {
            // 关键: curr num <= preSum, 不缺失, 只是 不是 "最小 len" 的 arr
            if (arrIdx < nums.length && nums[arrIdx] <= missing) {
                missing += nums[arrIdx++];
            } else {
                // 如果超过了组成完整序列的必要数字(missing)大小时, 这时 nums[i] > missing), 无法满足组成完整序列, arr 加上 missing 才會是 "最少 patch 的次數".
                missing += missing; // 直接給 arr 補上 "正好缺的 missing 值"
                patches++;
            }
        }
        return patches;
    }

    public static void main(String[] args) {
        int[] nums = {1,};
        Q330 q330 = new Q330();
        int i = q330.minPatches(nums, 20);
    }
}
