package cn.buoy.leetcode.array;

public class Q11 {
    /*
    宽度只会越来越小, 如果高度不增加, 最大面积只可能越来越小.
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int water = 0, l = 0, r = height.length - 1;
        while (l < r) {
            //保留需要移动的指针的当时高度.
            int h = Math.min(height[l], height[r]);
            water = Math.max(water, (r - l) * h);
            //移动需要移动的左或右指针, 直到找到比这个指针大的那个index.
            while (l < r && height[l] <= h)
                l++;
            while (l < r && height[r] <= h)
                r--;
        }
        return water;
    }
}
