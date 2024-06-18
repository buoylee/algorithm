package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q282 {
    /**
     * https://www.youtube.com/watch?v=v05R1OIIg08
     * 停下思路就好, 然后看下边代码, 好理解
     */
    public List<String> addOperators(String numStr, int target) {
        List<String> res = new ArrayList();
        //base case:
        if (numStr.length() == 0 || Long.parseLong(numStr) > Integer.MAX_VALUE)
            return res;
        //把numStr拆开成字节数组, 及长度.
        char[] digitArr = numStr.toCharArray();
        int n = digitArr.length;
        //表达式str最长len n + (n - 1).
        char[] expStr = new char[2 * n - 1];
        //当前的组成的数字.
        long curCal = 0;
        //从第i位开始, 到 arr结尾 组成 数字的组合, 然后放在expStr第i位.
        //numStr: 2134
        //expStr: [2, 21, 213, 2134...]
        for (int i = 0; i < n; i++) {
            curCal = curCal * 10 + digitArr[i] - '0';
            expStr[i] = digitArr[i];
            dfs(res, expStr, i + 1, digitArr, i + 1, 0, curCal, target);
            //如果存在leading zero, 直接退出.
            if (curCal == 0) break;
        }
        return res;
    }

    /**
     * @param result
     * @param expStr        temp str
     * @param expStart      当前未处理的 index 位
     * @param digitArr
     * @param digitArrStart
     * @param prevCal       前边 所有 运算 的结果
     * @param prevTempNum   当前的组合数字, 在这个函数里 就是上一次 数组组合的值.
     * @param target        目标结果
     */
    private void dfs(List<String> result, char[] expStr, int expStart, char[] digitArr, int digitArrStart, long prevCal, long prevTempNum, int target) {
        //遍历完整个digitArr, 检查是否 == target, 把exp加入result
        if (digitArrStart == digitArr.length) {
            if (prevCal + prevTempNum == target)
                result.add(new String(expStr, 0, expStart));
            return;
        }
        //为现在准备参与运算的数字初始化
        long currTempNum = 0;
        //这里先跳过应该放操作符(+, -, *)的位置`expIndex`, 留给下边for里添加.
        //为什么这里不考虑`什么操作符都不加`的情况, 因为 在上一层取数字的遍历中 已经考虑了. 这里只讨论 填什么操作符.
        int expIndex = expStart + 1;
        for (int digitIndex = digitArrStart; digitIndex < digitArr.length; digitIndex++) {
            //构建此次的数字
            currTempNum = currTempNum * 10 + digitArr[digitIndex] - '0';
            expStr[expIndex++] = digitArr[digitIndex];
            //每一层递归 都保留了 expStart, 每次遍历都会再从expStart开始, 不需要进行backtracking.
            expStr[expStart] = '+';
            //prevTempNum 是上次的数字组合
            //关键在 如何处理 * 的运算顺序,
            //prevCal, prevTempNum, currTempNum. 例如: prevCal + prevTempNum 和 prevCal - prevTempNum, 如果 prevCal 和 prevTempNum 之间是*, 那就需要currTempNum的参与运算才能加入'再'之前的运算值.
            //分别是 `上上个被 + 或 - 隔开的之前所有运算的结果; 上一次的 数字组合; 当前的数字组合;
            //关键代码: 延迟了 上一次数字的运算操作.
            //在当前轮, 如果是*, 就要先运算与前数字的值, 直到 下次遇到不是*的情况, 才能 和prevCal做运算.
            dfs(result, expStr, expIndex, digitArr, digitIndex + 1, prevCal + prevTempNum, currTempNum, target);
            expStr[expStart] = '-';
            dfs(result, expStr, expIndex, digitArr, digitIndex + 1, prevCal + prevTempNum, -currTempNum, target);
            expStr[expStart] = '*';
            //这里还是不能和 prevCal 运算, 直到后续出现 +, -
            dfs(result, expStr, expIndex, digitArr, digitIndex + 1, prevCal, prevTempNum * currTempNum, target);
            //0开头的数字不合法, 跳出这个start的遍历
            if (currTempNum == 0) break;
        }
    }
}
