package cn.buoy.leetcode.matrix;

public class Q48 {
    /**
     * 簡單, 視頻. 註釋.
     * https://www.youtube.com/watch?v=9ryIfj5DohI
     * 思路: 知道公式, 1. 上下鏡像顛倒; 2. \(左上到右下)對角線鏡像顛倒. 結束.
     * <p>
     * clockwise rotate
     * first reverse up to down, then swap the symmetry
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
     */
    public void rotate(int[][] matrix) {
        // 上下鏡像顛倒
        int s = 0, e = matrix.length - 1;
        // 跳過中線
        while (s < e) {
            int[] temp = matrix[s];
            matrix[s] = matrix[e];
            matrix[e] = temp;
            s++;
            e--;
        }
        // \(左上到右下)對角線鏡像顛倒, 跳過對角線.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 題外, 逆時針轉.
     * anticlockwise rotate
     * first reverse left to right, then swap the symmetry
     * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
     * 可以用上边的方法逆时针转, 也可以上边的while 和 下边的for换位.
     */
}
