package cn.buoy.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

public class Q54 {
    /**
     * 简单, 視頻, 邊界問題. 复习
     * https://www.youtube.com/watch?v=dfGhf-Ko0L4 其實這個代碼的邊界比較好理解.
     * https://www.youtube.com/watch?v=mmQfavfpW_M 短.
     * 思路: 每走完一行/列, 修改 限制(不能走到該行/列).
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0) return res;
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        // 關鍵: 邊界問題, 因爲每次過完1行/列,都會馬上跳到下一行/列, 所以,  Begin == rowEnd 時, 是可以再走一行/列的.
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++)
                res.add(matrix[rowBegin][j]);
            rowBegin++;
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++)
                res.add(matrix[j][colEnd]);
            colEnd--;
            // 關鍵: 因爲在 while (rowBegin <= rowEnd && colBegin <= colEnd) 之後, 再一次 rowBegin++ 和 colEnd--, 所以 需要多一次判斷.
            // 因为即使 有一边是 begin > rowEnd, 但 另一边 是可以 rowBegin <= rowEnd 的
            if (rowBegin <= rowEnd)
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j--)
                    res.add(matrix[rowEnd][j]);
            rowEnd--;
            // 關鍵: 因爲在 while (rowBegin <= rowEnd && colBegin <= colEnd) 之後, 再一次 rowBegin++ 和 colEnd--, 所以 需要多一次判斷.
            if (colBegin <= colEnd)
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j--)
                    res.add(matrix[j][colBegin]);
            colBegin++;
        }
        return res;
    }
}
