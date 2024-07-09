package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q30 {
    /**
     * 明白思路的話, 簡單, 視頻, 代碼細節需要複習.
     * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-6/
     * https://www.youtube.com/watch?v=n9fYwG3dC_Q
     * 思路: 通過 word 的使用情況(all word count, pre word count)來, 控制滑動窗口.
     */
    public List<Integer> findSubstring(String s, String[] words) {
        //  記錄 word 和 word的次數
        final Map<String, Integer> wordMap = new HashMap<>();
        for (final String word : words)
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        final int fullStrLen = s.length();
        final int numsOfWords = words.length;
        final int wordLen = words[0].length();
        final List<Integer> result = new ArrayList<>();
        // 關鍵: 爲什麼 結尾可以寫成 i < wordLen, 
        // 因爲每個word長度是一定的, 當 i = wordLen 時, 其實  i = 0 的情況包含了 i = wordLen, 等於跳過了第一個 word, 進行的查找.
        // i = 1, 等於跳過'第一個 letter' 進行搜索.
        for (int i = 0; i < wordLen; i++) {
            // 滿足 used.get(word) <= wordMap.get(word) 時的 substr 左邊界.
            int left = i;
            // 使用了幾個 '合法' 的word, 這裏只統計 '存在的' word 數量, 超出的部分, 由各自的 wordMap.get(word) 處理.
            int usedWordCount = 0;
            final Map<String, Integer> used = new HashMap<>();
            // 檢查到結尾 fullStrLen - wordLen
            for (int j = i; j <= fullStrLen - wordLen; j += wordLen) {
                final String word = s.substring(j, j + wordLen);
                // 存在這個 word
                if (wordMap.containsKey(word)) {
                    // used++
                    used.put(word, used.getOrDefault(word, 0) + 1);
                    if (used.get(word) <= wordMap.get(word)) {
                        usedWordCount++;
                    } else {
                        // 刪掉 過多的 word
                        while (used.get(word) > wordMap.get(word)) {
                            // 移除窗口 最左边的 word
                            final String first = s.substring(left, left += wordLen);
                            // 恢復 used, --
                            used.put(first, used.getOrDefault(first, 0) - 1);
                            // 如果這個 used word 返還後, 比 使用上限 小, 說明 彈出了一個 需要的 word, 需要 usedWordCount--.
                            if (used.get(first) < wordMap.getOrDefault(first, 0))
                                usedWordCount--;
                        }
                    }
                    // 爲什麼不用判斷 wordMap.get(word) 是負數的情況, 因爲前邊 檢查了 used.get(word) <= wordMap.get(word),
                    // 一旦超過, 就會馬上減去 最左邊 的 word, 直到 恢復到 可用最大值.
                    if (usedWordCount == numsOfWords)
                        result.add(left);
                } else {
                    // 如果进来了不需要的单词, 跳过一个 word len 继续.
                    used.clear();
                    usedWordCount = 0;
                    left = j + wordLen;
                }
            }
        }
        return result;
    }

    /**
     * 超時了, 草
     * https://www.youtube.com/watch?v=L6NLra-rZoU
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        if (s == null || words == null || words.length == 0)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int numsOfWords = words.length;
        int wordLen = words[0].length();
        HashMap<String, Integer> map = new HashMap<>();
        //  記錄 word 和 word的次數
        for (String str : words)
            map.put(str, map.getOrDefault(str, 0) + 1);
        // 因爲後續需要滿足 numsOfWords * wordLen 的長度.
        for (int fullStrIndex = 0; fullStrIndex <= s.length() - numsOfWords * wordLen; fullStrIndex++) {
            HashMap<String, Integer> copy = new HashMap<>(map);
            // 用來判斷 words len 是不是都用完了, words 用完時, 檢查 words 對應使用次數 是不是都剛好 歸0 了.
            int numsOfRemainWords = numsOfWords;
            // 用來記錄, 每次組成的 word 在 fullStr的 index.
            int wordStartIndex = fullStrIndex;
            // 能否 找出 一個 words 組成的 substr.
            while (numsOfRemainWords > 0) {
                // 摳出 一個 單詞
                String str = s.substring(wordStartIndex, wordStartIndex + wordLen);
                // 超出 使用次數, 直接失敗.
                if (!copy.containsKey(str) || copy.get(str) < 1)
                    break;
                copy.put(str, copy.get(str) - 1);
                // 剩餘 words 個數 --
                numsOfRemainWords--;
                // 下一個 word 在 fullStr 中的 index
                wordStartIndex += wordLen;
            }
            // 剛好 歸0, 符合, 加入結果.
            if (numsOfRemainWords == 0) result.add(fullStrIndex);
        }
        return result;
    }
}
