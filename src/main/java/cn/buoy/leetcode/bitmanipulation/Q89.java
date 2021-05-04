package cn.buoy.leetcode.bitmanipulation;

import java.util.LinkedList;
import java.util.List;

public class Q89 {
    /**
     * https://www.youtube.com/watch?v=Pp6LLp5_RFs
     * 背
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1 << n; i++)
            result.add(i ^ i >> 1);
        return result;
    }
}
