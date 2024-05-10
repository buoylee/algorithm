package cn.buoy.leetcode.array;

public class Q4 {
    /*
    https://www.youtube.com/watch?v=ScCg9v921ns
    O(log(min(1.length, 2.length)))
    //fixme 边界 好烦(使得方便得到对应的中位数和num1, num2的左右用语比较的边界index)
    //fixme 思路: 中位数(即位于整合后的有序数组的中间位置, 直接得出中间index,
       再通过bin, 利用条件:num1Left <= num2Right && num2Left <= num1Right, 来找到答案位置.)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        //选择较短的arr作为num1来2分,
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        //half total奇数时, 会 + 1, 中间; 为了方便取中位数, 就是当前中位数.
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
        //total 奇数
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
