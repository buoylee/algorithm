package cn.buoy.leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q139 {
    /**
     * https://www.youtube.com/watch?v=pYKGRZwbuzs 這個比較長
     * <p>
     * https://www.youtube.com/watch?v=H2EgWq-45CY 可以先看這個看看能不能回憶
     * 直接看視頻, 關聯: 140
     * 简单, 還是需要想清楚dp的上一步是什麼.
     * 思路: dp, 例如 A ->...-> F -> G, 如果你要從 A -> G, 那就要存在 A -> F == true, 然後找到存在的 F -> G 字符串;
     * 當然 A -> G 有多種方式, 需要全部檢查.例如 A -> D == true, 不過要找到 一個字符從 D -> F, 等等.
     */
    public boolean wordBreak(String str, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }
        // dp[i] 表示 前綴有 i 個字母可以匹配.
        boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true;

        // 之前寫的, 可以跳過這一長串.
        //dp[i], 表示的是 是否能组成 前i个substr,
        //从左边开始,
        //思路重点: 状态转移,
        //假如现在要求dp[3], 那我们就要考虑到`上一步`到`第3为str`的各种方式,
        //第一步约束, 从上一步 到 target 最多只有可能 wordDict.len 种 方式 到达, 有 pd[0]~pd[3], pd[1]~pd[3], pd[2]~pd[3], (前边变量为j, 后边3为i)
        //所以 只要`dp[j] 为 true(可成功拼凑到j位)`, 且`j~i的substr 存在在dict中, 则 dp[j]也是true(也能到达).
        //综上, 遍历上述所有可能, 只要有一个成功拼凑, dp[j]即为true.
        //i从1开始,substr 没问题.
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 關鍵: 存在 0 -> i 中, 任意一點 j, 使得 dp[j] == true, 且有一個 subStr 可以 從 str的 j -> i - 1.
                if (dp[j] && dict.contains(str.substring(j, i))) {
                    // 細節: 注意這裏的 i, dp[i]表示裏邊有i個字母, 對應的 str index 其實是 i-1(因爲str[]從0開始).
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[str.length()];


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
}
