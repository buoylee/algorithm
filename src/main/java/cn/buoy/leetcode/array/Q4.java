package cn.buoy.leetcode.array;

public class Q4 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=ScCg9v921ns
     * O(log(min(1.length, 2.length)))
     * 思路: 再通过bin search, 利用条件:num1Left(num1 切出的左邊) <= num2Right(num2 切出的右邊) && num2Left(num2 切出的左邊) <= num1Right(num1 切出的右邊), 来找到答案位置.
     * 關鍵: 中位數處理.
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        int low = 0, high = m;
        while (low <= high) {
            // 中位數 左半邊, 取 nums1 的長度
            int leftPartitionA = (low + high) / 2;
            // 中位數 左半邊, 取 nums2 的長度
            // 關鍵: m + n + 1 是因爲, 左半邊取多1位, 方便找中文數
            int leftPartitionB = (m + n + 1) / 2 - leftPartitionA;
            // 中位數 左半邊, nums1 取到的 subarr 最大值
            int LeftMaxA = (leftPartitionA == 0) ? Integer.MIN_VALUE : nums1[leftPartitionA - 1];
            // 中位數 左半邊, nums2 取到的 subarr 最大值
            int LeftMaxB = (leftPartitionB == 0) ? Integer.MIN_VALUE : nums2[leftPartitionB - 1];
            // 中位數 右半邊, nums1 取到的 subarr 最小值
            int rightMinA = (leftPartitionA == m) ? Integer.MAX_VALUE : nums1[leftPartitionA];
            // 中位數 右半邊, nums2 取到的 subarr 最小值
            int rightMinB = (leftPartitionB == n) ? Integer.MAX_VALUE : nums2[leftPartitionB];
            // 符合中位數的條件, "2個左邊的最大值" 都要分別比 "2個右邊的最小值" 小.
            if (LeftMaxA <= rightMinB && LeftMaxB <= rightMinA)
                if ((m + n) % 2 == 0) // 偶數
                    return (Math.max(LeftMaxA, LeftMaxB) + Math.min(rightMinA, rightMinB)) / 2.0;
                else // 奇數
                    return Math.max(LeftMaxA, LeftMaxB);
            else if (LeftMaxA > rightMinB) // arr A 左半邊取多了, 從當前 cut 左邊範圍找
                high = leftPartitionA - 1;
            else
                low = leftPartitionA + 1;
        }
        throw new IllegalArgumentException();
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //选择较短的arr作为num1来2分,
        if (m > n)
            return findMedianSortedArrays2(nums2, nums1);
        //half total奇数时, 会 + 1, 中间; 为了方便 index 取中位数, 就是当前中位数.
        //half total偶数时, 不变, 中间左边;
        int i = 0, j = 0, imin = 0, imax = m, half = (m + n + 1) / 2;
        double maxLeft = 0, minRight = 0;
        while (imin <= imax) {
            //奇数时, i为中, 0 + 5 / 2 = 2
            //偶数时, i为中间右边, 0 + 6 / 2 = 3
            i = (imin + imax) / 2; //num1 左边个数
            j = half - i; //num2 左边个数
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) { //下边 左边最后一个 > 上边 右边第一个
                imin = i + 1;
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                imax = i - 1;
            } else {
                if (i == 0) {
                    maxLeft = (double) nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = (double) nums1[i - 1];
                } else {
                    //成为half的那个数(就是中间数),就是上下左边最大的那个数.
                    maxLeft = (double) Math.max(nums1[i - 1], nums2[j - 1]);
                }
                break;
            }
        }
        // total 奇数
        if ((m + n) % 2 == 1) {
            return maxLeft;
        }
        //total 偶数
        if (i == m) {
            minRight = (double) nums2[j];
        } else if (j == n) {
            minRight = (double) nums1[i];
        } else {
            //选出上下右边第一个,就是右边最小的那个.
            minRight = (double) Math.min(nums1[i], nums2[j]);
        }

        return (double) (maxLeft + minRight) / 2;
    }

}
