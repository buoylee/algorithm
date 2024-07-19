package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q118 {
    /**
     * 簡單, 見過幾次了, 視頻
     * https://leetcode.com/problems/pascals-triangle/discuss/38141/My-concise-solution-in-Java
     * https://www.youtube.com/watch?v=QpLdVwe1hvs
     * 思路: 每次構建一行時, arr head 插個 '1', 然後 从index = 1开始, num[i] = num[i]+num[i+1], 直到 index = (len-1)-1.
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            // head 插 '1'
            temp.add(0, 1);
            // 從 index == 1 開始, num[i] = num[i]+num[i+1]
            for (int j = 1; j < temp.size() - 1; j++)
                temp.set(j, temp.get(j) + temp.get(j + 1));
            result.add(new ArrayList<>(temp));
        }
        return result;
    }
}
