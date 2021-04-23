package cn.buoy.leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
    /**
     * https://www.youtube.com/watch?v=v5XssGxx60U
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            //只有遍历过的元素 中 有 target - 当前元素 的差, 就会返回.
            if (map.containsKey(target - numbers[i])) {
                result[1] = i;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            //没有 遍历过的 元素可以组合, 加入到遍历过的map 中, <元素值, index>.
            map.put(numbers[i], i);
        }
        return result;
    }
}
