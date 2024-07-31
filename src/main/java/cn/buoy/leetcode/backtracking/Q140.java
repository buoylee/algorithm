package cn.buoy.leetcode.backtracking;

import java.util.*;

public class Q140 {
    /**
     * 簡單, 视频不好理解. 直接看下边代码. 下边代码太好理解了.
     * https://www.youtube.com/watch?v=pYKGRZwbuzs
     * 思路: 不斷 切出 preStr, 判斷是否存在于 wordDict, 有就繼續遞歸切 剩餘的 subStr, 當 startIdx 來到了 str.len, 說明是有效的解, 加入 result
     * 優化: 可以求出 最長 word 的 Len, 減少切 preSub 時的 遍歷次數.
     */
    // 最长的 word len
    private int maxWordLen = 0;

    public List<String> wordBreak(String str, List<String> wordDict) {
        // wordDict 转 set, 加速查找
        Set<String> dictSet = new HashSet<>();
        List<String> result = new ArrayList<>();
        // temp
        List<String> candidates = new ArrayList<>();
        for (String word : wordDict) {
            maxWordLen = Math.max(maxWordLen, word.length());
            dictSet.add(word);
        }
        dfs(str, result, candidates, dictSet, 0);
        return result;
    }

    /**
     *
     */
    private void dfs(String str, List<String> result, List<String> tempList, Set<String> dictSet, int startIdx) {
        // startIdx 可以成功来到 str 末尾, 把'tempList' 拼接成 string 后, 加入到 result 中.
        if (startIdx == str.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tempList.size() - 1; ++i)
                sb.append(tempList.get(i)).append(" ");
            // 去掉最后的空格.
            sb.append(tempList.get(tempList.size() - 1));
            result.add(sb.toString());
            return;
        }

        // 細節: 不能超過 startIdx + "dict 最大 len" 且 "原 str.len"
        for (int index = startIdx; index < startIdx + maxWordLen && index < str.length(); ++index) {
            // 从 startIdx 开始切除不同长度的 left 半邊, 如果 dictSet 中存在, 则加入 tempList
            String left = str.substring(startIdx, index + 1);
            if (dictSet.contains(left)) {
                tempList.add(left);
                dfs(str, result, tempList, dictSet, index + 1);
                // backtracking
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /**
     * 有空再看, 沒那麼直觀
     */
    public List<String> wordBreak3(String s, List<String> wordDict) {
        return wordBreak3(s, new HashSet<>(wordDict), new HashMap<>());
    }

    private List<String> wordBreak3(String s, Set<String> wordDict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> strToAllCombineList = new ArrayList<>();
        if (wordDict.contains(s)) {
            strToAllCombineList.add(s);
        }

        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            if (wordDict.contains(left)) {
                String right = s.substring(i);
                // 剩餘的 str 繼續 遞歸 切分.
                List<String> subResult = wordBreak3(right, wordDict, memo);
                // 關鍵: 所有的 str 組合 都放到 同一個 list 中.
                for (String sub : subResult) {
                    strToAllCombineList.add(left + " " + sub);
                }
            }
        }

        memo.put(s, strToAllCombineList);
        return strToAllCombineList;
    }


    public List<String> wordBreak2(String s, List<String> wordDict) {
        //wordDict 转set
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
//            length = Math.max(length, word.length());
            dict.add(word);
        }
        List<String> result = new ArrayList<>();
        //temp
        List<String> candidates = new ArrayList<>();

        helper2(s, result, candidates, dict, 0);
        return result;
    }


    private void helper2(String s, List<String> result, List<String> candidates, Set<String> dict, int index) {
        //可以成功来到末尾, 拼接好str 加入到result中.
        if (index == s.length()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < candidates.size() - 1; ++i) {
                builder.append(candidates.get(i)).append(" ");
            }
            //去掉最后的空格而已.
            builder.append(candidates.get(candidates.size() - 1));
            result.add(builder.toString());
            return;
        }

        StringBuilder builder = new StringBuilder();
        //拼接前边已经完成的str, 加上当前str, 如果满足前缀, 则往后递归.
        for (int i = 0; i < candidates.size(); ++i) {
            builder.append(candidates.get(i));
        }
        for (String w : dict) {
            StringBuilder finalTemp = builder.append(w);
            if (s.startsWith(finalTemp.toString())) {
                candidates.add(w);
                helper2(s, result, candidates, dict, index + w.length());
                candidates.remove(candidates.size() - 1);
            }
            builder.setLength(builder.length() - w.length());
        }
    }

    public static void main(String[] args) {
        Q140 q140 = new Q140();
        List<String> strings = q140.wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    }
}
