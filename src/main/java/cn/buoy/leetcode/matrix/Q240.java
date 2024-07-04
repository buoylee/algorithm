package cn.buoy.leetcode.matrix;

public class Q240 {
    /**
     * https://www.youtube.com/watch?v=V6Z3FTGhGwk
     * 思路: 還是找規律, 關鍵在右上角這個點, 该點有2个特点, 同一行最大, 比同一列最小;
     * 所以, 如果 target 比 這個點 小, 就在同行左邊;
     * 如果 target 比 這個點 大, 就出現在下邊所有行;
     * 如此重複操作, 直到找到.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (col >= 0 && row < matrix.length) {
            int lastElePerRow = matrix[row][col];
            if (target == lastElePerRow)
                return true;
            // 关键在检查 每一row 的最后 一位! 该位有2个特点, 同一行最大, 比同一列最小.
            // 所以, 如果小了, 肯定就在本行, 所以只用col--
            if (target < lastElePerRow)
                col--;
            else // 如果大了, 從下行開始找
                row++;
        }
        return false;
    }
}
