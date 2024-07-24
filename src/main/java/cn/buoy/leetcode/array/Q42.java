package cn.buoy.leetcode.array;

public class Q42 {
    /**
     * https://www.youtube.com/watch?v=EIFpXEzFIj8
     * https://www.youtube.com/watch?v=bu1quf2rOp8 短, 原理很清楚, 思路/實現稍微不同.
     * 思路: 头尾2指针, 向內收縮;
     * 当 height[left] <= height[right], 可以得知他們之間[left+1 ~ right-1], 如果有小於 height[left] 的 index, 就可以儲水. height[left] >= height[right]同理.
     * 小的一邊往裏收縮, 如果 遇到比 height[left] 小的, 很好, 記下儲水量, left++ 往內收縮;
     * 如果遇到比 height[left] 大(或等於)的, 這時, 沒法儲水, 但是我們知道, 後續能儲水的量邊多了, 即, 他的 maxleft(從左邊往右時, 發現最大的高度) 更新了.
     * <p>
     * maxleft 更新後, 就有可能出現上邊提到的 height[left] >= height[right] 情況, 這時, 我們就要向內收縮 right, 如果遇到比 height[right] 小的, 很好, 記下儲水量, right--;
     * 如果遇到比 height[right] 大(或等於)的, 這時, 沒法儲水, 但是我們知道, 後續能儲水的量邊多了, 即, 他的 maxright(從右邊往左時, 發現最大的高度) 更新了.
     * 如此反覆直到 left index > right index 越界.
     */
    public int trap(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int res = 0;
        int maxleft = 0; // 從左邊往右收縮時, 發現最大的高度
        int maxright = 0; // 從右邊往左收縮時, 發現最大的高度
        while (left <= right) {
            // 如果左小
            if (height[left] <= height[right]) {
                // 如果 [l] 比 maxleft 大, [l] 无法储水起始点, 更新 maxleft 为当前高度.
                if (height[left] >= maxleft)
                    maxleft = height[left];
                else // 累加水量:
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
            //fixme 这里更加简洁, 因为当高度相同时, 累积量等于0, 省去上个例子判断需要等于.
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
