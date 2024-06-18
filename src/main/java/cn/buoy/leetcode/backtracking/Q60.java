package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q60 {
    /**
     * https://www.youtube.com/watch?v=xdvPD1IiyUM
     * 有够难
     * 求!maxNum的所有组合中, 第orderNum的排列顺序.
     * <p>
     * 1,              2,              3,               4
     * n/!(4-1) == 6, n-1/!(4-2) == 4, n-2/!(4-3) == 1,
     *
     * 1234, 1243, 1324, 1342, 1423, 1432,
     * 2134, 2143, 2314, 2314, 2413, 2431,
     * ...
     * 一定要先观察规律, 被拿走中间的数后, 仍然按照原来的顺序由小到继续排列组合.
     */
    public String getPermutation(int maxNum, int orderNum) {
        int pos = 0;

        // factorial[] = {1, 1, 2, 6, 24, ... n!}
        int[] factorial = new int[maxNum + 1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= maxNum; i++) {
            sum *= i;
            factorial[i] = sum;
        }

        // create a list of numbers to get indices
        //从1开始到n
        // numbers = {1, 2, 3, 4}
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= maxNum; i++) {
            numbers.add(i);
        }

        //为了和numbers数组的index对应.
        orderNum--;

        for (int i = 1; i <= maxNum; i++) {
            //k在此轮哪个区间里.
            //第1轮: n种可能; 第2轮: n-1种可能; 第3轮: n-2种可能;
            // 思路要转过来: 现在要找第一位数字的位置, 那就要从1~n内找出来,
            // 所以这里的 总数 与 需要的 被除数 分别是:
            // (1): !n, !(n-1);
            // (2): !(n-1), !(n-2);...

            int index = orderNum / factorial[maxNum - i]; // 4 - 1; 4 - 2; 4 - 3;
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            //下边2行相等, 一个是 orderNum 减去 前边index个(如果k在 从0数 第 index == 1, 就要减去1个factorial[n - i])区间所有元素 得到 我们要的元素 在 第几位; 一个是 取余, 直接拿到该区间第几位.
//            orderNum -= index * factorial[n - i];
            orderNum %= factorial[maxNum - i];

        }

        return String.valueOf(sb);
    }
}
