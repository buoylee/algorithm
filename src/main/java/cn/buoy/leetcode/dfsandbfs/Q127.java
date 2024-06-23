package cn.buoy.leetcode.dfsandbfs;

import java.util.*;

public class Q127 {

    public static void main(String[] args) {
        Q127 q127 = new Q127();
//        ArrayList<String> wordList = new ArrayList<String>;
        String[] strings = {"hot", "dot", "dog", "lot", "log", "cog"};
//        boolean b = wordList.add(strings);
        List list = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        q127.ladderLength("hit", "cog", list);
    }


    /**
     * https://www.youtube.com/watch?v=0fzUFMpGLMU
     * 基本思路就是經典的bfs, 關鍵在於如何構建這個tree(不能重複使用元素).
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 關鍵: 用於排除使用過的words
        Set<String> availableWords = new HashSet<>(wordList);
        // 題目提供.
        if (!availableWords.contains(endWord))
            return 0;
        int ansult = 0;
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(beginWord));
        while (!queue.isEmpty()) {
            ++ansult;
            // 某層的所有元素, 在下層元素加入 queue 前, 先記錄本層元素個數.
            for (int sz = queue.size(); sz > 0; --sz) {
                StringBuilder sb = new StringBuilder(queue.poll());
                // 找出某個 word 的所有鄰居(除了之前使用過的).
                for (int i = 0; i < sb.length(); ++i) {
                    // 每次替換完都要換回原char, 因爲還要檢查 word 的下一個 char.
                    final char initChar = sb.charAt(i);
                    for (char c = 'a'; c <= 'z'; ++c) {
                        // 替換char
                        sb.setCharAt(i, c);
                        final String word = sb.toString();
                        // 到 end 直接返回
                        if (word.equals(endWord))
                            return ansult + 1;
                        // available 存在該替換後的 word, 放入 queue, 並從 available 移除.
                        if (availableWords.contains(word)) {
                            queue.offer(word);
                            // 關鍵: 後續只能使用還在 wordSet 的 words.
                            availableWords.remove(word);
                        }
                    }
                    sb.setCharAt(i, initChar);
                }
            }
        }
        return 0;
    }


    /**
     * 頭尾双向BFS
     * https://www.youtube.com/watch?v=vWPCm69MSfs
     * <p>
     * https://leetcode.com/problems/word-ladder/discuss/40711/Two-end-BFS-in-Java-31ms.
     * <p>
     * 原理: 在bfs基礎上優化, 使得 extend 變小(單向 extend 會越來越大, 雙向使尾 extend 和頭一樣.)
     * 加入到start, end set 的元素 都是 start, end 能到达的元素
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        Set<String> availableWords = new HashSet<>(wordList);
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        int length = 1;
        startSet.add(beginWord);
        endSet.add(endWord);
        availableWords.remove(beginWord);
        availableWords.remove(endWord);

        while (!startSet.isEmpty()) {
            // startSet 當前 extend 一層的 words
            Set<String> next = new HashSet<>();
            // 只用startSet遍歷, 因爲後邊會選 size 小的作爲 startSet.
            for (String word : startSet) {
                char[] wordArray = word.toCharArray();
                //某個word 的 neighbour
                for (int i = 0; i < word.length(); i++) {
                    char initChar = wordArray[i];
                    //每一位不同字母比较
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[i] = c;
                        String str = String.valueOf(wordArray);
                        //准备在 startSet 插入的str, 如果存在于 endSet, 则完成.
                        if (endSet.contains(str))
                            return length + 1;
                        //如果不存在于 endSet, 则检查 availableWords 是否有, 有则加入 startSet.
                        if (availableWords.contains(str)) {
                            next.add(str);
                            availableWords.remove(str);
                        }
                    }
                    wordArray[i] = initChar;
                }
            }
            // 用 size 小的作爲 startSet 用於遍歷, 提高速度.
            startSet = next.size() < endSet.size() ? next : endSet;
            endSet = next.size() < endSet.size() ? endSet : next;
            length++;
        }
        return 0;
    }
}
