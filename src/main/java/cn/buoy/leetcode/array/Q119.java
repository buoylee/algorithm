package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q119 {
    /*
    和Q118类似
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0)
            return list;

        for (int i = 0; i < rowIndex + 1; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
        }
        return list;
    }

    /*
    num[2]
    外层循环每次结果
    [1,0...0]
    [1,1...0]
    [1,2,1...0]
    [1,3,3,1...0]
    [1,4,6,4,1...0]
    ...
     */
    public static List<Integer> getRow2(int rowIndex) {
        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 0);
        result[0] = 1;
        for (int i = 1; i < rowIndex + 1; i++)
            for (int j = i; j >= 1; j--)
                result[j] += result[j - 1];
        return Arrays.asList(result);
    }

}
