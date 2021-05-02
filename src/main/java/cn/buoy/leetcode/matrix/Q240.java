package cn.buoy.leetcode.matrix;

public class Q240 {
    /**
     * https://www.youtube.com/watch?v=V6Z3FTGhGwk
     * 原理: 看第一行最后一位, 先`只下移`找到 小于 target 的 某行最后一位, 再`只左移`直到 `大于等于` target 结束.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1) {
            int now = matrix[row][col];
            //关键在检查 每一row 的最后 一位! 该位有2个特点, 比前边任何元素 都大(前所有行, 同一行).
            if (target == now)
                return true;
            if (target < now) {
                //因为从左上角(第一行)开始找, 如果小了, 肯定就在本行, 所以只用col--
                col--;
            } else {
                //如果大了, 肯定就在本行, 所以只用col--
                row++;
            }
        }
        return false;
    }
}
