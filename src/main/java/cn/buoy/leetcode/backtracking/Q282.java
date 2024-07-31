package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q282 {
    /**
     * 其实是简单的, 听下视频思路就好, 看注释代码, 好理解.
     * https://www.youtube.com/watch?v=v05R1OIIg08
     * 思路: 遍历 numStr 各个长度的 preStr, 然后 把 preStr 传入下层 dfs, 再次用剩余 numStr 切出 第2个 preStr, 分 "+/-/*" 3种情况继续 dfs.
     * 关键: prevCal currNum nextNum 运算顺序.
     * 如果 currNum 和 nextNum 之间是 "+/-", 可以直接运算 prevCal 和 currNum, 运算后的结果作为 "新的 prevCal" 传入下一层 dfs
     * 如果 currNum 和 nextNum 之间是 "*", 则先运算 currNum 和 currNum, 运算后的结果作为 "新的 currNum" 传入下一层 dfs
     */
    public List<String> addOperators(String numStr, int target) {
        List<String> res = new ArrayList();
        if (numStr.length() == 0 || Long.parseLong(numStr) > Integer.MAX_VALUE)
            return res;
        // numStr 转为 char[]
        char[] digitArr = numStr.toCharArray();
        int numStrLen = digitArr.length;
        // 表达式 str 最长 numStrLen, 因为中间可以多插 numStrLen - 1 个运算符
        char[] expStr = new char[2 * numStrLen - 1];
        // 当前的组成的数字.
        long currNum = 0;
        // 切出 digitArr 的 preStr, 作为 "下一个 num"
        for (int i = 0; i < numStrLen; i++) {
            currNum = currNum * 10 + digitArr[i] - '0';
            expStr[i] = digitArr[i];
            dfs(res, expStr, i + 1, digitArr, i + 1, 0, currNum, target);
            // 只有 '0' 合法, 不能出现 leading zero 的 多位数, 跳过.
            if (currNum == 0) break;
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
     * @param currNum       本次运算 的第一个 num
     * @param target        目标结果
     */
    private void dfs(List<String> result, char[] expStr, int expStart, char[] digitArr, int digitArrStart, long prevCal, long currNum, int target) {
        // 遍历完整个digitArr, 检查是否 总结果 == target, 把exp加入result
        if (digitArrStart == digitArr.length) {
            if (prevCal + currNum == target)
                result.add(new String(expStr, 0, expStart));
            return;
        }
        // 为现在准备参与运算的数字初始化
        long nextNum = 0;
        // 这里先 预留出 操作符(+/-/*)的位置 `expIndex`, 下边for里再处理.
        // 为什么这里不考虑 `不加操作符` 的情况, 因为 在上一层 "切 preStr" 时, 已确定 这里放运算符的位置.
        int expIndex = expStart + 1;
        for (int digitIndex = digitArrStart; digitIndex < digitArr.length; digitIndex++) {
            // 构建参与运算的第2个数
            nextNum = nextNum * 10 + digitArr[digitIndex] - '0';
            // 关键: 这种写法很巧妙, 这样记录了 "运算符 index", 然后从 "运算符后的 index" 开始遍历, 就不用显式的写 backtracking
            expStr[expIndex++] = digitArr[digitIndex];
            // 关键: prevCal, currNum, nextNum 的运算先后顺序:
            // currNum 和 nextNum 的运算符是 "+/-", 则可以先执行 prevCal 和 currNum 的运算;
            // 如果是 '*', 保留 prevCal, 执行 "prevCal 和 currNum 的运算", 直到后续层再次遇到 currNum 和 nextNum 的运算符为 "+/-", 才可以和 prevCal 运算.
            expStr[expStart] = '+';
            dfs(result, expStr, expIndex, digitArr, digitIndex + 1, prevCal + currNum, nextNum, target); // '+': prevCal + currNum
            expStr[expStart] = '-';
            dfs(result, expStr, expIndex, digitArr, digitIndex + 1, prevCal + currNum, -nextNum, target); // '-': prevCal + currNum
            expStr[expStart] = '*';
            dfs(result, expStr, expIndex, digitArr, digitIndex + 1, prevCal, currNum * nextNum, target); // '*': currNum * nextNum
            // 只有 '0' 合法, 不能出现 leading zero 的 多位数, 跳过.
            if (nextNum == 0) break;
        }
    }
}
