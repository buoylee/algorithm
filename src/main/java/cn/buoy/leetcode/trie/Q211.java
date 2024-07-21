package cn.buoy.leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q211 {
    /**
     * 簡單, 視頻, trie
     * https://www.youtube.com/watch?v=RRnRv991JxI
     * 思路: trie. search 就是 dfs 一直往下檢查.
     */
    class WordDictionary {
        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c))
                    node.children.put(c, new TrieNode());
                node = node.children.get(c);
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            return search(word, root, 0);
        }

        /**
         * dfs 一直往下檢查
         *
         * @param word
         * @param node
         * @param index word index
         * @return
         */
        private boolean search(String word, TrieNode node, int index) {
            if (index == word.length())
                return node.isEnd;
            char c = word.charAt(index);
            // dfs a~z
            if (c == '.') {
                for (TrieNode child : node.children.values())
                    if (search(word, child, index + 1))
                        return true;
                return false;
            } else {
                if (!node.children.containsKey(c))
                    return false;
                return search(word, node.children.get(c), index + 1);
            }
        }

        private class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            boolean isEnd = false;
        }
    }


    // 先看上邊的
    class WordDictionary2 {

        /**
         * Initialize your data structure here.
         */

        private Map<Integer, List<String>> lenToWords;

        public WordDictionary2() {
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
