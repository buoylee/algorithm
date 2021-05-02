package cn.buoy.leetcode.matrix;

public class Q48 {
    /**
     * https://www.youtube.com/watch?v=9ryIfj5DohI
     * 由外层到内层(x,y都 ++ 就是内圈)rotate, `左上边角`到`右上边角-1` 为1组, 因为右边角在下一组 也会像上一组作为左边角移动, 所以不用重复.
     */
    // TODO: 2021/5/2

    /**
     * 下边的好写一点点.
     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     */
    public void rotate(int[][] matrix) {
        int s = 0, e = matrix.length - 1;
        while (s < e) {
            int[] temp = matrix[s];
            matrix[s] = matrix[e];
            matrix[e] = temp;
            s++;
            e--;
        }

        /**
         * anticlockwise rotate
         * first reverse left to right, then swap the symmetry
         * 1 2 3     3 2 1     3 6 9
         * 4 5 6  => 6 5 4  => 2 5 8
         * 7 8 9     9 8 7     1 4 7
         * 可以用上边的方法逆时针转, 也可以上边的while 和 下边的for换位.
         */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
