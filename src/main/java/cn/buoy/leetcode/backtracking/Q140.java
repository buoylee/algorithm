package cn.buoy.leetcode.backtracking;

import java.util.*;

public class Q140 {
    public static void main(String[] args) {
        Q140 q140 = new Q140();
        List<String> strings = q140.wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    }

    /**
     * https://www.youtube.com/watch?v=pYKGRZwbuzs
     * <p>
     * 第2个实现, 自己想的, 比较容易理解.
     */
    // 最长len的wordDict元素 的len
    private int length = 0;

    public List<String> wordBreak(String s, List<String> wordDict) {
        //wordDict 转set
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            length = Math.max(length, word.length());
            dict.add(word);
        }
        List<String> result = new ArrayList<>();
        //temp
        List<String> candidates = new ArrayList<>();

        helper(s, result, candidates, dict, 0);
        return result;
    }

    /**
     * @param s
     * @param result
     * @param candidates temp list
     * @param dict
     * @param index      成功拼接到 str的index 的下一位.
     */
    private void helper(String s, List<String> result, List<String> candidates, Set<String> dict, int index) {
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

        //遍历继续条件, 必须肯定是小于index + dict最大len 且 如果到达了 str 末尾, 则不能超过 原str.len
        //关键在这里, 这里的遍历范围, 以 index++ 来思考, 当 遍历到 len 时, substr 出来, 对比dict元素.
        for (int i = index; i < index + length && i < s.length(); ++i) {
            String str = s.substring(index, i + 1);
            if (dict.contains(str)) {
                candidates.add(str);
                helper(s, result, candidates, dict, i + 1);
                candidates.remove(candidates.size() - 1);
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
