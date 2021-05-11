package cn.buoy.leetcode.array;

public class Q88 {
    /**
     * https://www.youtube.com/watch?v=91bi3bIrW3I
     * 从后往前排就简单了
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //末尾index
        //刚好又将m, n 移动到2arr 末尾元素index
        int k = (m--) + (n--) - 1;
        while (m >= 0 && n >= 0) {
            //谁大放谁到k, 然后--
            nums1[k--] = nums2[n] > nums1[m] ? nums2[n--] : nums1[m--];
        }
        //注意这里就好, 如果剩的是num1, 不用处理, 就是他本来的位置
        //只需处理剩余的nums到index到0即可.
        while (n >= 0) nums1[k--] = nums2[n--];
    }

}
