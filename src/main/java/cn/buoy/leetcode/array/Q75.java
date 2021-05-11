package cn.buoy.leetcode.array;

public class Q75 {
    /**
     * https://www.youtube.com/watch?v=AFAwe8uBWv8
     * 简单!
     * 3指针, i当前遍历到的index, low表示low之前的元素都是0,high表示high之后的元素都是2.
     */
    public void sortColors(int[] A) {
        if (A == null || A.length < 2) return;
        int low = 0;
        int high = A.length - 1;
        for (int i = low; i <= high; ) {
            if (A[i] == 0) {
                //swap到low, low++
                // swap A[i] and A[low] and i,low both ++
                swap(A, i, low);
                i++;
                low++;
            } else if (A[i] == 2) {
                //swap到high, high--
                //swap A[i] and A[high] and high--;
                swap(A, i, high);
                high--;
            } else {
                //遇1 则保持原位
                i++;
            }
        }
    }

    void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }
}
