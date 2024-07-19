package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q119 {
    /**
     * 可以跳過. 什麼東西啊, 有Q118, 出這個幹嘛. 只是算出最後一行.
     * https://www.youtube.com/watch?v=OKCpLpT_qYI
     * 思路: 每次構建一行時, arr head 插個 '1', 然後 从index = 1开始, num[i] = num[i]+num[i+1], 直到 index = (len-1)-1.
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0)
            return list;
        for (int i = 0; i < rowIndex + 1; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++)
                list.set(j, list.get(j) + list.get(j + 1));
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
