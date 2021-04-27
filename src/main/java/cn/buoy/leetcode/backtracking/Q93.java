package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return res;
        helper(s, res, list, 0);
        return res;

    }

    private void helper(String s, List<String> res, List<String> list, int index) {
        if (list.size() == 4) {
            //段数满足, 但是 原str 还没到末尾, 则失败.
            if (index != s.length()) return;
            //满足时, 拼结果str add 进 res.
            StringBuilder sb = new StringBuilder();
            for (String tmp : list) {
                sb.append(tmp);
                sb.append(".");
            }
            //去最后的`.`
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            return;

        }

        //遍历每段的各种可能, 每段只能是1~3位. 分3情况递归. 从现在的index位开始.
        for (int i = index; i < s.length() && i < index + 3; i++) {
            //记得加1, substring 不包括最后一位.
            String tmp = s.substring(index, i + 1);
            if (isvalid(tmp)) {
                list.add(tmp);
                helper(s, res, list, i + 1);
                //backtracking
                list.remove(list.size() - 1);
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

    /**
     * 这个视频不用backtracking, 因为 temp 按值传递.
     * https://www.youtube.com/watch?v=b8_w2ljAzeU
     */
    // TODO: 2021/4/27
}
