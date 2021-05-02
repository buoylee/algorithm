package cn.buoy.leetcode.matrix;

public class Q378 {
    /**
     * https://www.youtube.com/watch?v=1VkP3Ndu1C4
     */
    public int kthSmallest(int[][] matrix, int k) {
        //将最大最小value 作为 low high
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        //离开while后, 都会
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            //j 是 矩阵的 宽
            int count = 0, j = matrix[0].length - 1;
            //i 是 矩阵的 高
            for (int i = 0; i < matrix.length; i++) {
                while (j >= 0 && matrix[i][j] > mid)
                    j--;
                //count 统计 小于等于 mid 的个数.
                //每次2分 都统计一次.
                count += (j + 1);
            }
            //最后一步lo, hi相邻, 相邻 2分后 肯定是落在 前者index.
            //因为k肯定存在,排除掉 <k后, 就是lo后一位, 如果是 >= k 就是2分后的index.
            if (count < k)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
        //都行
//        return hi;
    }
}
