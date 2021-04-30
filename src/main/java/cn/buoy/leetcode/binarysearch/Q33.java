package cn.buoy.leetcode.binarysearch;

public class Q33 {
    /**
     * https://www.youtube.com/watch?v=lWEIIFFflQY
     * 画图! 就好理解了!
     */
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]) {
                //mid出现在左边区域
                if (target < nums[mid] && target >= nums[start])
                    //target 出现在start mid 之间, 说明在左区.
                    end = mid - 1;
                else
                    //在右区, 需要 移动 start 到右区.
                    start = mid + 1;
            }
            if (nums[mid] <= nums[end]) {
                //mid出现在右边区域
                if (target > nums[mid] && target <= nums[end])
                    //target 在 mid 和 end 之间的话, 移动start到右区.
                    start = mid + 1;
                else
                    //target 大于 mid 且 end, 这target在左区, end 移动到 左区.
                    end = mid - 1;
            }
        }
        return -1;
    }
}
