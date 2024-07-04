package cn.buoy.leetcode.matrix;

public class Q311 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=VGSfnZQQf5w
     * 矩阵基本乘法 (x,y)*(y*z) 只有y相等才能乘, 最后的结果是(x,z)的大小.
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int rowA = A.length, colA = A[0].length, colB = B[0].length;
        int[][] C = new int[rowA][colB];

        for (int rowAI = 0; rowAI < rowA; rowAI++) {
            for (int colAI = 0; colAI < colA; colAI++) {
                // 0, continue
                if (A[rowAI][colAI] != 0) {
                    for (int colBI = 0; colBI < colB; colBI++) {
                        // 0, continue
                        if (B[colAI][colBI] != 0)
                            // sum
                            C[rowAI][colBI] += A[rowAI][colAI] * B[colAI][colBI];
                    }
                }
            }
        }
        return C;
    }
}
