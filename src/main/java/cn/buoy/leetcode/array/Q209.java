package cn.buoy.leetcode.array;

public class Q209 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=jp15K7dTCHc
     * 思路: slide window, 同向双指针. 因为是正整数, 所以可以用双指针, "移动右指针" 用于 "增加 sum", "移动左指针" 用于 "减少 sum".
     */
    public int minSubArrayLen(int target, int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int left = 0, right = 0, sum = 0, minSize = Integer.MAX_VALUE;
        // 右指针移动, 使
        for (right = 0; right < arr.length; right++) {
            sum += arr[right];
            // 一旦达到要求 sum >= target, 计算长度, 然后 移动左指针, 直到再次小于 target
            while (sum >= target) {
                // 注意长度的计算: 當 left == right 時, "subArr 長度" 其实是 1;
                minSize = Math.min(minSize, right - left + 1);
                sum -= arr[left++];
            }
        }
        // 不要忘记, 如果还是出现 minSize  == MAX_VALUE, 说明是没有解, 返回0
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }
}
