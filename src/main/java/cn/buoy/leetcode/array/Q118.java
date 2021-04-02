package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Q118 {
    /*
    https://leetcode.com/problems/pascals-triangle/discuss/38141/My-concise-solution-in-Java

    数组头加1, 从index = 1开始, num[i] = num[i]+num[i+1], 直到 index = (len-1)-1, 忘了就看链接.
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allrows = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            allrows.add(new ArrayList<>(row));
        }
        return allrows;

    }
}
