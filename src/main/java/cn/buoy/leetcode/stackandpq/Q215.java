package cn.buoy.leetcode.stackandpq;

import java.util.PriorityQueue;

public class Q215 {

    /**
     * 簡單, 快排 直到处于第k位
     * https://www.youtube.com/watch?v=zOmIKYKpzB4
     * 思路: 快排
     */
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // pos + 1 就是第k大数
            int pos = findPosition(nums, left, right);
            if (pos == k - 1) {
                return nums[pos];
            } else if (pos > k - 1) { // 选定的数字小了, 结果在 pos 左边
                right = pos - 1;
            } else
                left = pos + 1; // 选定的数字大了, 结果在 pos 右边
        }
        return -1;
    }

    private int findPosition(int[] nums, int left, int right) {
        // 中位數
        int pivot = nums[left];
        // 避開 pivot
        int l = left + 1;
        int r = right;
        // 使 curr 左边的值都大于它, 右边的值都小于它
        // 細節: 要使用 <=
        // 否则[2,1] 1 这种情况会报错
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l, r);
                l++;
                r--;
            }
            // 跳过符合条件的值
            if (nums[l] >= pivot)
                l++;
            if (nums[r] <= pivot)
                r--;
        }
        // 中間數(left) 和 比中間數'大或等'的數(r) 交換.
        swap(nums, left, r);
        return r;
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /**
     * 最简单
     */
    public int findKthLargest2(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

}
