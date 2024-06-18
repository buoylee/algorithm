package cn.buoy.leetcode.backtracking;

import java.util.*;

public class Q140 {
    public static void main(String[] args) {
        Q140 q140 = new Q140();
        List<String> strings = q140.wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    }

    /**
     * https://www.youtube.com/watch?v=pYKGRZwbuzs
     * 视频不好理解.
     * 直接看下边代码. 下边代码太好理解了. 很传统的回溯.
     */
    // 最长len的wordDict元素 的len
    private int length = 0;

    public List<String> wordBreak(String str, List<String> wordDict) {
        //wordDict 转set
        Set<String> dictSet = new HashSet<>();
        List<String> result = new ArrayList<>();
        //temp
        List<String> candidates = new ArrayList<>();
        for (String word : wordDict) {
            length = Math.max(length, word.length());
            dictSet.add(word);
        }

        helper(str, result, candidates, dictSet, 0);
        return result;
    }

    /**
     *
     */
    private void helper(String str, List<String> result, List<String> tempList, Set<String> dictSet, int start) {
        //可以成功来到str末尾, 把'tempList' 拼接成string后, 加入到result中.
        if (start == str.length()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < tempList.size() - 1; ++i) {
                builder.append(tempList.get(i)).append(" ");
            }
            //去掉最后的空格而已.
            builder.append(tempList.get(tempList.size() - 1));
            result.add(builder.toString());
            return;
        }

        //遍历继续条件, 必须肯定是小于start + dict最大len 且 如果到达了 str 末尾, 则不能超过 原str.len
        for (int index = start; index < start + length && index < str.length(); ++index) {
            //从start开始切除遍历不同长度的subStr, 如果dictSet中存在, 则加入tempList
            String subStr = str.substring(start, index + 1);
            if (dictSet.contains(subStr)) {
                tempList.add(subStr);
                helper(str, result, tempList, dictSet, index + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
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
}
