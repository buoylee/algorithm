package cn.buoy.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q368 {
    /**
     * https://www.youtube.com/watch?v=YzCbp19FugM
     */
    // if we sort the array, every element in a divisibleSubset can be divisible by the element just before it.
    // for any element k, its largestDivisibleSubset that ends with k can be formed in the following way:
    // use element k after any one of the previous elements that is divisble
    public List<Integer> largestDivisibleSubset(int[] nums) {
        //每个数的 为最大值 时 的 最大subset 个数.
        int[] l = new int[nums.length]; // the length of largestDivisibleSubset that ends with element i
        //为了 最大subset 的每个元素, 需要记录 使 当前subset 最大的 上一个(可以整除的那个比他小)的元素 的index.
        //当完成了 最大subset个数的统计, 就可以先找到最大subset的那个元素, 然后一路往会找, 直到 某个元素可整除的元素的index 等于 -1 结束, 看代码就懂了!
        int[] prev = new int[nums.length]; // the previous index of element i in the largestDivisibleSubset ends with element i
        //排序后, 使只需要用 当前数 去除 以往的值 就好
        Arrays.sort(nums);

        int max = 0;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            l[i] = 1;
            prev[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                //只要能和 前边的数整除,
                if (nums[i] % nums[j] == 0 && l[j] + 1 > l[i]) {
                    l[i] = l[j] + 1;
                    prev[i] = j;
                }
            }
            //update max subset 最大个数 及其的 index, 方便后边直接找回元素.
            if (l[i] > max) {
                max = l[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        //直接用最大subset的index
        while (index != -1) {
            res.add(nums[index]);
            index = prev[index];
        }
        return res;
    }
}
