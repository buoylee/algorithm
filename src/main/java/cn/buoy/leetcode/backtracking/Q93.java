package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q93 {

    /**
     * https://www.youtube.com/watch?v=b8_w2ljAzeU
     * 视频参考思路就好, 用的是递归. 代码用下边的backtracking比较直观和容易记.
     */

    public List<String> restoreIpAddresses(String str) {
        List<String> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        if (str.length() < 4 || str.length() > 12) return res;
        helper(str, res, list, 0);
        return res;

    }

    private void helper(String str, List<String> result, List<String> temp, int start) {
        //IP段数满足
        if (temp.size() == 4) {
            //但是 原str 还没到末尾, 则失败.
            if (start != str.length()) return;
            //满足时, 拼结果str add 进 res.
            StringBuilder sb = new StringBuilder();
            for (String tmp : temp) {
                sb.append(tmp);
                sb.append(".");
            }
            //去最后的`.`
            sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
            return;
        }

        //遍历每段中的各种可能, 每段只能是1~3位.
        for (int i = start; i < str.length() && i < start + 3; i++) {
            //记得加1, substring 不包括最后一位.
            String tmp = str.substring(start, i + 1);
            if (isvalid(tmp)) {
                temp.add(tmp);
                helper(str, result, temp, i + 1);
                //backtracking
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isvalid(String s) {
        //当第一个digit为0时, 如果s 不是 "0", 无效.
        if (s.charAt(0) == '0')
            return s.equals("0");        // to eliminate cases like "00", "10"
        int digit = Integer.valueOf(s);
        return digit >= 0 && digit <= 255;
    }

}
