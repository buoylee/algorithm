package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q60 {
    /**
     * https://www.youtube.com/watch?v=xdvPD1IiyUM
     * 有够难
     */
    public String getPermutation(int n, int k) {
        int pos = 0;
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
            factorial[i] = sum;
        }
        // factorial[] = {1, 1, 2, 6, 24, ... n!}

        // create a list of numbers to get indices
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        // numbers = {1, 2, 3, 4}

        k--;

        for (int i = 1; i <= n; i++) {
            //k在第几轮(从0数)区间里.
            int index = k / factorial[n - i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            //下边2行相等, 一个是 k减去 前边index个(如果k在 从0数 第 index == 1, 就要减去1个factorial[n - i])区间所有元素 得到 我们要的元素 在 第几位; 一个是 取余, 直接拿到该区间第几位.
//            k -= index * factorial[n - i];
            k %= factorial[n - i];

        }

        return String.valueOf(sb);
    }
}
