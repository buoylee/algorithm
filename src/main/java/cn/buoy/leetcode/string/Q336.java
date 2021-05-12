package cn.buoy.leetcode.string;

import java.util.*;

public class Q336 {
    /**
     * https://www.youtube.com/watch?v=P5-bM5g4m5Q
     * 有2种解法:
     * 1. map<value, index>, 方便查找和时候同一元素.
     * 2种情况, 如果是 有"", 只要另一个是回文即可; 长短 str, 按照 拼接顺序, 去掉头 尾开始, 剩下是回文即可.
     */
    /**
     * 直观, 好实现.
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<>();
        if (words == null || words.length < 2) return ret;
        Map<String, Integer> map = new HashMap<String, Integer>();
        //放map 好找
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        //遍历每个元素
        for (int i = 0; i < words.length; i++) {
            // System.out.println(words[i]);
            //切2半,或不切
            for (int j = 0; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                //前半部是回文的话, 找map中是否存在 后半部的rvs 即可.
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        ret.add(list);
                        // System.out.printf("isPal(str1): %s\n", list.toString());
                    }
                }
                //后半部是回文的话, 找map中是否存在 前半部的rvs 即可.
                if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length() != 0) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        ret.add(list);
                        // System.out.printf("isPal(str2): %s\n", list.toString());
                    }
                }
            }
        }
        return ret;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) return false;
        }
        return true;
    }


    /**
     * trie解法
     */
    TrieNode root;
    List<List<Integer>> result;

    public List<List<Integer>> palindromePairs2(String[] words) {
        root = new TrieNode();
        result = new ArrayList<>();
        if (words == null || words.length == 0)
            return result;

        for (int i = 0; i < words.length; i++) {
            addWord(words[i], i);
        }
        //遍历每一个元素, 是否有存在的回文配对.
        for (int i = 0; i < words.length; i++) {
            search(words[i], i);
        }

        return result;
    }

    private void search(String word, int wordIndex) {
        TrieNode node = root;
        char[] letters = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            int c = letters[i] - 'a';

            //wordIndex 表示到了某个单词的头
            //i开始 到 末尾 是否是回文.
            //例 abc|aaa, 找到了有一个 单词的reverse 也是 abc, 就可以组合成 当前遍历的word + 有反向的单词 node.wordIndex
            if (node.wordIndex != -1 && isPalindrome(letters, i, letters.length - 1))
                result.add(Arrays.asList(wordIndex, node.wordIndex));

            //完全不存在直接返回
            if (node.childrens[c] == null)
                return;

            //遍历下一个node
            node = node.childrens[c];
        }

        // search的word 走到尾, 且有单词, 放入res. 例如 abc, abc(cba的reverse)
        if (node.wordIndex != -1 && node.wordIndex != wordIndex) {
            result.add(Arrays.asList(node.wordIndex, wordIndex));
        }

        //如果word走到末尾, 但是当前node 不是反转单词的末尾, 但是 接下来的是回文, 例如: 123, 123|444(444|321的reverse), 插入.
        for (int j : node.restIsPalindrome) {
            result.add(Arrays.asList(wordIndex, j));
        }
    }

    private void addWord(String word, int index) {
        TrieNode node = root;
        char[] letters = word.toCharArray();

        //反向插入
        for (int i = letters.length - 1; i >= 0; i--) {
            int c = letters[i] - 'a';
            //从该点开始之后的的单词 为回文, 并标记是那个单词(index)的.
            if (isPalindrome(letters, 0, i)) {
                node.restIsPalindrome.add(index);
            }

            if (node.childrens[c] == null) {
                node.childrens[c] = new TrieNode();
            }
            node = node.childrens[c];
        }
        //在尾部加上单词index
        node.wordIndex = index;
    }

    private boolean isPalindrome(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start++] != arr[end--]) {
                return false;
            }
        }

        return true;
    }
}

class TrieNode {
    TrieNode[] childrens;
    int wordIndex;
    List<Integer> restIsPalindrome;

    public TrieNode() {
        childrens = new TrieNode[26];
        this.wordIndex = -1;
        restIsPalindrome = new ArrayList<>();
    }
}
