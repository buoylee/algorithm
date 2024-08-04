package cn.buoy.leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q139 {
    /**
     * 140
     * 简单, 视频
     * https://www.youtube.com/watch?v=pYKGRZwbuzs 這個比較長
     * https://www.youtube.com/watch?v=H2EgWq-45CY 可以先看這個看看能不能回憶
     * 思路: 假设, 我们遍历到了 strArr[i], 就是 "求 dp[i]", 我们就 要在 0~i-1 之间 找找看, 有没有 dp[x] + 一个 word 刚好到 strArr[i], 有则 dp[i] = true;
     */
    public boolean wordBreak(String str, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        // 加速查找
        for (String word : wordDict)
            dict.add(word);
        // dp[i] 表示, str 的 "前 i 個字母" 是否可以匹配.
        boolean[] dp = new boolean[str.length() + 1];
        // 0 个 char 默认匹配
        dp[0] = true;
        // 关键: 从 "str 前1个 char" 开始找, 一路到 "str 前n 个 char", 看能否到达,
        for (int i = 1; i <= str.length(); i++) {
            // 关键: 内层循环 就是要找到是否有 "之前的 "dp == true" "一步走到 i" 的 word
            for (int j = 0; j < i; j++) {
                // 關鍵: 存在 0 -> i 中, 任意一點 j, dp[j] == true, 且有一個 subStr 可以 從 str的 j -> i - 1.
                if (dp[j] && dict.contains(str.substring(j, i))) {
                    // 細節: 注意這裏的 i, dp[i]表示裏邊有i個字母, 對應的 str index 其實是 i-1(因爲str[]從0開始).
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[str.length()];
    }

    // second DP
//        for(int i = 1; i <= s.length(); i++){
//            for(String str: dict){
//                if(str.length() <= i){
//                    if(f[i - str.length()]){
//                        if(s.substring(i-str.length(), i).equals(str)){
//                            f[i] = true;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        return dp[str.length()];

}
