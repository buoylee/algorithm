package cn.buoy.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Q290 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=DE_1PgWudNA
     * 思路: 爲了保證 唯一 pattern 的 char 對應 唯一 s 的 substr,
     * 需要檢查 char -> substr, 同時對應同一對 substr -> char
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) return false;
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            // 沒就 插入 一對, 有就 檢查是否與 "之前的配對" 一致.
            if (charToWord.containsKey(c))
                if (!charToWord.get(c).equals(word))
                    return false;
                else
                    charToWord.put(c, word);
            // 因爲需要一一對應, 所以 需要反過來檢查, 同理上邊
            if (wordToChar.containsKey(word))
                if (!wordToChar.get(word).equals(c))
                    return false;
                else
                    wordToChar.put(word, c);
        }
        return true;
    }
}
