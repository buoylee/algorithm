package cn.buoy.leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q139 {
    /**
     * https://www.youtube.com/watch?v=pYKGRZwbuzs
     * 相对简单
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // public boolean wordBreak(String s, Set<String> dict) {
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }
        boolean[] f = new boolean[s.length() + 1];

        f[0] = true;

        //First DP
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

        //Second DP
        //dp[i], 表示的是 是否能组成 前i个substr,
        //从左边开始,
        //思路重点: 状态转移,
        //假如现在要求dp[3], 那我们就要考虑到`上一步`到`第3为str`的各种方式,
        //第一步约束, 从上一步 到 target 最多只有可能 wordDict.len 种 方式 到达, 有 pd[0]~pd[3], pd[1]~pd[3], pd[2]~pd[3], (前边变量为j, 后边3为i)
        //所以 只要`dp[j] 为 true(可成功拼凑到j位)`, 且`j~i的substr 存在在dict中, 则 dp[j]也是true(也能到达).
        //综上, 遍历上述所有可能, 只要有一个成功拼凑, dp[j]即为true.
        //i从1开始,substr 没问题.
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[s.length()];
    }
}
