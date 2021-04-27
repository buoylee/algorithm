package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q282 {
    /**
     * https://www.youtube.com/watch?v=v05R1OIIg08
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        //base case:
        if (num.length() == 0 || Long.parseLong(num) > Integer.MAX_VALUE)
            return res;
        //字节数组, 及长度.
        char[] cs = num.toCharArray();
        int n = cs.length;
        //表达式str最长len n + (n - 1).
        char[] expStr = new char[2 * n - 1];
        //当前的组成的数字.
        long cur = 0;
        //从第i位开始, 到 arr结尾 组成 数字的组合, 然后放在expStr第i位.
        for (int i = 0; i < n; i++) {
            cur = cur * 10 + cs[i] - '0';
            expStr[i] = cs[i];
            dfs(res, expStr, i + 1, cs, i + 1, 0, cur, target);
            //如果存在leading zero, 直接退出.
            if (cur == 0) break;
        }
        return res;
    }

    /**
     * @param res
     * @param expStr   temp str
     * @param expIndex 当前未处理的 index 位
     * @param cs
     * @param csIndex
     * @param left     前边 所有 运算 的结果
     * @param cur      当前的组合数字, 在这个函数里 就是上一次 数组组合的值.
     * @param target   目标结果
     */
    private void dfs(List<String> res, char[] expStr, int expIndex, char[] cs, int csIndex, long left, long cur, int target) {
        if (csIndex == cs.length) {
            if (left + cur == target)
                res.add(new String(expStr, 0, expIndex));
            return;
        }
        //当前这轮 数的 可能值, digit++
        long val = 0;
        //累加 val 的同时, 放数字的expIndex ++
        //这里跳过了操作符的位置`expIndex`, 留给下边添加.
        //为什么这里不考虑`什么操作符都不加`的情况, 因为 在上一层去数字的遍历中 已经考虑了. 这里只讨论 填 什么操作符.
        int j = expIndex + 1;
        for (int i = csIndex; i < cs.length; i++) {
            val = val * 10 + cs[i] - '0';
            expStr[j++] = cs[i];
            //每一层递归 都保留了 expIndex, 每次都会再从该index开始递归, 不需要进行backtracking.
            expStr[expIndex] = '+';
            //cur 是上次的数字组合
            //关键在 如何处理 * 的运算顺序,
            //left, cur, val.
            //分别是 `最左边` 到 `不包括上一次数字` 的运算结果; 上一次的 数字组合; 当前的数字组合;
            //关键代码: 延迟了 上一次数字的运算操作.
            //在当前轮, 如果是*, 就要先运算与前数字的值, 直到 下次遇到不是*的情况, 才能 和left做运算.
            dfs(res, expStr, j, cs, i + 1, left + cur, val, target);
            expStr[expIndex] = '-';
            dfs(res, expStr, j, cs, i + 1, left + cur, -val, target);
            expStr[expIndex] = '*';
            //这里还是不能和 left运算, 直到后续出现 +, -
            dfs(res, expStr, j, cs, i + 1, left, cur * val, target);
            if (val == 0) break;
        }
    }
}
