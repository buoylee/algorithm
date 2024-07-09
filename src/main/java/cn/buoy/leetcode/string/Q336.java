package cn.buoy.leetcode.string;

import java.util.*;

public class Q336 {
    /**
     * 有2种解法: 1. 切分找 reverse; 2. trie解法;
     * 解法一可以選擇不看視頻. 解法一有空再看
     * <p>
     * 這個解法, 容易理解, 直观, 好实现. 可以直接看題目, 代碼.
     * https://www.youtube.com/watch?v=P5-bM5g4m5Q
     * 1. word 任意切爲2段 , 如果其中一段是迴文, 只要 另一段的 reverse 存在於 words 中, 就可以組成迴文.
     * 要分別尋找 前/後 的 reverse, 因爲存在組成時 2個 word 的"先後關係", 例如 1. ccba, ab; 2. bacc, ab
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) return result;
        Map<String, Integer> map = new HashMap<String, Integer>();
        // 方便查詢 index
        for (int i = 0; i < words.length; i++)
            map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            // 關鍵:
            for (int j = 0; j <= words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String firstPart = words[i].substring(0, j);
                String secondPart = words[i].substring(j);
                // firstPart 是回文的话, 就要找到 最終組合的 左半邊(secondPart 的 reverse)
                if (isPalindrome(firstPart)) {
                    String rvsSecondPart = new StringBuilder(secondPart).reverse().toString();
                    if (map.containsKey(rvsSecondPart)
                            && map.get(rvsSecondPart) != i) { // 細節: 如果 word 是 "", 不能重複使用 "", 組成迴文
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(rvsSecondPart));
                        list.add(i);
                        result.add(list);
                    }
                }
                // secondPart 是回文的话, 就要找到 最終組合的 右半邊(firstPart 的 reverse)
                if (isPalindrome(secondPart)) {
                    String rvsFirstPart = new StringBuilder(firstPart).reverse().toString();
                    // 不能重複使用 word
                    if (map.containsKey(rvsFirstPart)
                            && map.get(rvsFirstPart) != i
                            && secondPart.length() != 0) { // 細節: 去重; 例如: "", "xxx"(任意str) 配對
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(rvsFirstPart));
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right)
            if (str.charAt(left++) != str.charAt(right--)) return false;
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
