package cn.buoy.leetcode.math;

public class Q360 {
    /**
     * 簡單, 只是考察了函數y的大小排序問題, 搞清楚規律的話. 視頻講的不好, 看題, 看註釋比較快.
     * https://www.youtube.com/watch?v=2b7f197Jw6k 講的不好其實. 看視頻解決不了關鍵點.
     * 思路: 2元2次方程, 開口向上/向下,
     * 關鍵規律: 有 start end 2點, 他們之間(start++ end--),
     * 如果開口向上, 從2點開始往內遍歷比較, 大那個的一定是最大的(除了之前比較的).
     * 如果開口向下, 從2點開始往內遍歷比較, 小那個的一定是最小的(除了之前比較的).
     */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //x 数组长度
        int len = nums.length;
        //结果数组
        int[] result = new int[len];
        //头尾指针
        int i = 0, j = len - 1;
        //看2次方系数, 判断开口上下, 根據上邊註釋的規律, 判斷從大還是小的開始排.
        int index = a >= 0 ? len - 1 : 0;
        while (i <= j) {
            //开口向上
            //2边到中间查找, 大的那个肯定是最大的
            if (a >= 0) {
                result[index--] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c)
                        ? quad(nums[i++], a, b, c)
                        : quad(nums[j--], a, b, c);
            } else {
                //开口向下
                //2边到中间查找, 小的的那个肯定是最大的
                result[index++] = quad(nums[i], a, b, c) >= quad(nums[j], a, b, c)
                        ? quad(nums[j--], a, b, c)
                        : quad(nums[i++], a, b, c);
            }
        }
        return result;
    }

    //求解y
    private int quad(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
