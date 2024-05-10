package cn.buoy.leetcode.array;

public class Q11 {
    /*
    思路: 宽度只会越来越小, 如果高度不增加, 最大面积只可能越来越小.
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int maxWaterVolumn = 0, left = 0, right = height.length - 1;
        while (left < right) {
            //取出2者中最矮的, 移动指针后用比它大的替换掉它.
            int h = Math.min(height[left], height[right]);
            maxWaterVolumn = Math.max(maxWaterVolumn, (right - left) * h);
            //移动需要移动的左或右指针, 直到找到比这个指针大的那个index.
            while (left < right && height[left] <= h)
                left++;
            while (left < right && height[right] <= h)
                right--;
        }
        return maxWaterVolumn;
    }
}
