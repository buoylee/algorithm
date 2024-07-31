package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q93 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=b8_w2ljAzeU
     * 思路: backtracking, ip分4段, 每段遍歷 1/2/3位 3種可能, 判斷有效性; 當到達 分段數量 爲4, 且str index 到達末尾, 就是有效的ip, 加入result
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        dfs(result, new StringBuilder(), s, 0, 0);
        return result;
    }

    /**
     * @param result
     * @param temp
     * @param str
     * @param start   當前分段的 起始 index
     * @param segment 當前處理的第幾分段
     */
    private void dfs(List<String> result, StringBuilder temp, String str, int start, int segment) {
        if (segment == 4 && start == str.length()) {
            result.add(temp.toString());
            return;
        }
        // 不是上邊剛好都到達結尾的情況, 且來到了需要結束的位置: 取了4個 segment 或 str 到頭.
        if (segment == 4 || start == str.length())
            return;
        // 關鍵: 遍歷 1/2/3位 3種可能
        for (int len = 1; len <= 3 && start + len <= str.length(); len++) {
            String part = str.substring(start, start + len);
            if (isValid(part)) {
                // 記錄未被修改前的 len 長度, 用於稍後 backtrack
                int beforeLength = temp.length();
                if (segment > 0) temp.append(".");
                temp.append(part);
                dfs(result, temp, str, start + len, segment + 1);
                // backtrack
                temp.setLength(beforeLength);
            }
        }
    }

    // 2個作用: 多位數不能以0開頭; 大小只能在 0~255;
    private boolean isValid(String part) {
        // 如果 segment 有多位數字, 如果以 0 開頭, 則爲無效數.
        if (part.length() > 1 && part.charAt(0) == '0') return false;
        int value = Integer.parseInt(part);
        return value >= 0 && value <= 255;
    }


    public List<String> restoreIpAddresses2(String str) {
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
