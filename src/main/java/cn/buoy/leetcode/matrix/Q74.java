package cn.buoy.leetcode.matrix;

public class Q74 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=R5mcIwLBtWI 这个方法有点直觉, 先确定在那一行(2分), 再确定是其中的 index(2分).
     * https://www.youtube.com/watch?v=JMDoEaNeieU 短, 這個也很直覺.
     * 思路: 当list来2分, matrix[start / col][start % col] 就是對應的 matrix的值.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        if (matrix[0] == null || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row * col - 1;
        // 典型2分
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int value = matrix[mid / col][mid % col];
            if (value == target)
                return true;
            else if (value < target)
                start = mid;
            else
                end = mid;
        }
        if (matrix[start / col][start % col] == target)
            return true;
        if (matrix[end / col][end % col] == target)
            return true;
        return false;
    }

    /**
     * 另一種2fen寫法
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
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
