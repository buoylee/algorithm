package cn.buoy.leetcode.math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q15 {
    public static void main(String[] args) {
        Q15 q15 = new Q15();
        List<List<Integer>> lists = q15.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }

    /**
     * https://www.youtube.com/watch?v=zMfD98y7Pec
     * 3指针
     *
     * @param num
     * @return
     */
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            //i 去重, 这里没问题, 去的是 上一轮 除了 第一个left 外的重复.
            if (i > 0 && num[i] == num[i - 1]) continue;
            int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    //low high 去重
                    while (lo < hi && num[lo] == num[lo + 1]) lo++;
                    while (lo < hi && num[hi] == num[hi - 1]) hi--;
                    lo++;
                    hi--;
                } else if (num[lo] + num[hi] < sum) lo++;
                else hi--;
            }
        }
        return res;
    }
}
