package cn.buoy.leetcode.matrix;

public class Q73 {
    /**
     * 簡單, 視頻, 註釋.
     * https://www.youtube.com/watch?v=-I8w2_sN93c
     * 思路:
     * 1. 利用首行/列格子, 來標記該列/行存在0; 關鍵: 首行/列 要在最後處理, 不然還是會影響其他點的判斷.
     * 2. 遍历從 index == 1 開始的每一个格子, 如果'對應的行/列的首格'是0, 将对应的整行/列都置0,
     * 3. 例外點 matrix[0][0], 它同時代表了0行/列 是否存在0, 所以需要用2個變量(firstRow, firstCol)來存.
     */
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false, firstCol = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // 關鍵: 第一行出現 0
                    if (i == 0) firstRow = true;
                    // 關鍵: 第一列出現 0
                    if (j == 0) firstCol = true;
                    // 關鍵: 該格子的對應的行的第一格子 改爲0
                    matrix[0][j] = 0;
                    // 關鍵: 該格子的對應的列的第一格子 改爲0
                    matrix[i][0] = 0;
                }
            }
        }
        //skip 过 index == 1 的行/列
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最後再處理 index == 0 的行
        if (firstRow)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[0][j] = 0;
        // 最後再處理 index == 0 的列
        if (firstCol)
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
    }
}
