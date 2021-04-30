package cn.buoy.leetcode.binarysearch;

public class Q81 {
    /**
     * https://www.youtube.com/watch?v=eG27FBcmy1k
     * 区别于 33, 会出现相等的元素, 即会出现水平的情况.
     */
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return true;
            }
            if (nums[left] < nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid]) {
                //在找mid 的时候, 如果 出现 [left] 等于 [mid], 无法判断 用 left 还是 right 替代 mid,
                //但是我们知道肯定会出现在中间, 这个时候 我们可以通过 不断左移 left, 再计算[mid], 来判断target在哪个区, 最后再回到之前的判断逻辑.
                left++;
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
