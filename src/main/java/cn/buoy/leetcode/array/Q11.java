package cn.buoy.leetcode.array;

public class Q11 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=A0w4BskM4VM
     * 思路: 首位雙指針. 由2端出發, 向內搜索更高的index.
     * 如何移動指針? 因爲 area 高度由小的一方決定, 所以, 只有移動並找到 "比原先較低的邊界" 高的 index, 才有可能得到更大的 area.
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int maxWaterVolumn = 0, left = 0, right = height.length - 1;
        while (left < right) {
            // 關鍵: 取較低的一方的高度, 因爲 area 由矮的一方決定.
            int h = Math.min(height[left], height[right]);
            maxWaterVolumn = Math.max(maxWaterVolumn, (right - left) * h);
            // 關鍵: 移动 "較低的 height" 的那個指针, 直到找到比上一次高的 index 才停止, 這時才有可能出現更大的 area.
            while (left < right && height[left] <= h)
                left++;
            while (left < right && height[right] <= h)
                right--;
        }
        return maxWaterVolumn;
    }
}
