package cn.buoy.leetcode.array;

public class Q42 {
    /*
    https://www.youtube.com/watch?v=EIFpXEzFIj8
    2指针l = 0, r = len - 1 头尾一个. 2边往里走.
    当 l <= r, 可以算出l + 1 的 水高度, 因为右边再大, 也会受到l 的限制; 右边再小, 因为受 r 影响, 也不可能低于 l 高度.
    同理 l >= r.
     */
    public int trap(int[] height) {
//        int[] height= height;
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int res = 0;
        int maxleft = 0, maxright = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                //如果 [l] 比 maxleft都大时, [l] 无法储水. 并设置maxleft为当前高度.
                if (height[left] >= maxleft)
                    maxleft = height[left];
                else
                    res += maxleft - height[left];
                left++;
            } else {
                if (height[right] >= maxright)
                    maxright = height[right];
                else
                    res += maxright - height[right];
                right--;
            }
        }
        return res;
    }

    /*
    https://www.youtube.com/watch?v=EIFpXEzFIj8
    https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
    o(2n)
    先 0 到 n-1 求出 i 左边最高高度,
    再求 0 到 n-1 求出 i 左边最高高度,
    遍历 0 到 n-1 ,比较 [i]的 min(i左高, ide右高), 然后减 i 高度, 累计水量返回结果.
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int result = 0;
        // leftMax represents the highest bar from left
        int leftMax = Integer.MIN_VALUE;
        // rightMax represents the highest bar from right
        int rightMax = Integer.MIN_VALUE;

        // use two pointers to scan the entire array until they meet with each other
        // Key points: any bars in the middle of leftMax bar and rightMax bar will not influence
        // how much water can current position trap
        for (int left = 0, right = height.length - 1; left <= right; ) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            //how much can current position trap depends on the shorter bar (木桶原理)
            if (leftMax < rightMax) {
                //DO NOT FORGET to subtract bar height of current position
                result += leftMax - height[left];
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}
