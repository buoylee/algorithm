package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Q247 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=hM2WMOfht_g
     * 思路: 由中间开始往外加 "一对 char"
     */
    public List<String> findStrobogrammatic(int n) {
        HashMap<String, String> digitMap = new HashMap<>();
        digitMap.put("0", "0");
        digitMap.put("1", "1");
        digitMap.put("6", "9");
        digitMap.put("8", "8");
        digitMap.put("9", "6");
        List<String> res = new ArrayList<>();
        // 奇数, 所有中位数可能.
        if (n % 2 == 1) {
            dfs(res, "0", digitMap, n - 1);
            dfs(res, "8", digitMap, n - 1);
            dfs(res, "1", digitMap, n - 1);
        } else
            dfs(res, "", digitMap, n);
        return res;
    }

    private void dfs(List<String> result, String tempStr, HashMap<String, String> digitMap, int remainNum) {
        if (remainNum == 0) {
            result.add(tempStr);
            return;
        }
        // 遍历所有 digitMap
        for (String key : digitMap.keySet()) {
            // 最外一层, 头部不能是0
            if (remainNum == 2 && key == "0") continue;
            dfs(result, key + tempStr + digitMap.get(key), digitMap, remainNum - 2);
        }
    }

    // 不够直观
    public List<String> findStrobogrammatic2(int n) {
        return helper(n, n);
    }

    // now 表示现在 str 长度; total 表示 总共需要的 长度
    List<String> helper(int now, int total) {
        if (now == 0) return new ArrayList<String>(Arrays.asList(""));
        if (now == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        //递归处理
        List<String> list = helper(now - 2, total);
        //处理最外层,
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            // 只要不是处理 最外层的数字(now == total 就是处理最外层时的循环)
            if (now != total)
                res.add("0" + s + "0");
            // 所有组合方式, 都加入到 本层 res
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
}
