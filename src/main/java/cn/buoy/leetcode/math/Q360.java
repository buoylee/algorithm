package cn.buoy.leetcode.math;

public class Q360 {
    /**
     * the problem seems to have many cases a>0, a=0,a<0, (when a=0, b>0, b<0). However, they can be combined into just 2 cases: a>0 or a<0
     * <p>
     * 1.a>0, two ends in original array are bigger than center if you learned middle school math before.
     * <p>
     * 2.a<0, center is bigger than two ends.
     * <p>
     * so use two pointers i, j and do a merge-sort like process. depending on sign of a, you may want to start from the beginning or end of the transformed array. For a==0 case, it does not matter what b's sign is.
     * The function is monotonically increasing or decreasing. you can start with either beginning or end.
     * 画图就懂了
     */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //x 数组长度
        int n = nums.length;
        //结果数组
        int[] sorted = new int[n];
        //头尾指针
        int i = 0, j = n - 1;
        //看2次方系数, 判断开口上下.
        int index = a >= 0 ? n - 1 : 0;
        while (i <= j) {
            //开口向上
            //2边到中间查找, 大的那个肯定是最大的
            if (a >= 0) {
                sorted[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c)
                        ? quad(nums[i++], a, b, c)
                        : quad(nums[j--], a, b, c);
            } else {
                //开口向下
                //2边到中间查找, 小的的那个肯定是最大的
                sorted[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c)
                        ? quad(nums[j--], a, b, c)
                        : quad(nums[i++], a, b, c);
            }
        }
        return sorted;
    }

    //求解y
    private int quad(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
