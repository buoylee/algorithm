package cn.buoy.leetcode.matrix;

public class Q311 {
    /**
     * https://www.youtube.com/watch?v=VGSfnZQQf5w
     * 矩阵基本乘法 (x,y)*(y*z) 只有y相等才能乘, 最后的结果是(x,z)的大小.
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;
    }
}
