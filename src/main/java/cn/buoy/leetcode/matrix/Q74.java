package cn.buoy.leetcode.matrix;

public class Q74 {
    /**
     * https://www.youtube.com/watch?v=R5mcIwLBtWI 这个方法有点直觉, 先确定在那一行(2分), 再确定是其中的 index(2分).
     * 关键: 当list来看, 刚好被 matrix[0].length 整除的list
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0, rows = matrix.length, cols = matrix[0].length;
        int end = rows * cols - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid / cols][mid % cols] == target) {
                return true;
            }
            if (matrix[mid / cols][mid % cols] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
