package cn.buoy.leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q211 {
    /**
     * trie 都可以看本地那个数据结构视频
     * https://www.youtube.com/watch?v=RRnRv991JxI
     * search方法 用递归
     * 差别只在于`.`, 需要遍历所有的下一个字母的可能
     */
    class WordDictionary {

        /**
         * Initialize your data structure here.
         */

        private Map<Integer, List<String>> lenToWords;

        public WordDictionary() {
            lenToWords = new HashMap<>();
        }

        public void addWord(String word) {
            lenToWords.putIfAbsent(word.length(), new ArrayList<>());
            lenToWords.get(word.length()).add(word);
        }

        public boolean search(String word) {
            int len = word.length();
            if (!lenToWords.containsKey(len)) return false;
            for (String candidate : lenToWords.get(len)) {
                //不是退出, 继续匹配
                if (match(word, candidate)) {
                    return true;
                }
            }
            //都不匹配才返回false
            return false;
        }

        //当前字母是否匹配输入的对应字母
        private boolean match(String pattern, String word) {
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) != '.' && pattern.charAt(i) != word.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
