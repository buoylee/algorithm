package cn.buoy.leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q288 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=rADGHkBFS5U
     * 思路: "待檢查 word" 沒有和 "原 words" 相同的 abbr, unique;
     * "待檢查 word" 有和 "原 words" 相同的 abbr, 原 words 只能有1個元素, 且 word 相同, unique;
     */
    class ValidWordAbbr {
        // map<abbr: list<word>>
        private HashMap<String, HashSet<String>> abbreviationMap;

        public ValidWordAbbr(String[] dictionary) {
            abbreviationMap = new HashMap<>();
            for (String word : dictionary) {
                String abbr = getAbbreviation(word);
                abbreviationMap.putIfAbsent(abbr, new HashSet<>());
                abbreviationMap.get(abbr).add(word);
            }
        }

        public boolean isUnique(String word) {
            String abbr = getAbbreviation(word);
            // 沒有相同的 abbr, unique
            // No other word in the data structure has the same abbreviation
            if (!abbreviationMap.containsKey(abbr))
                return true;
            HashSet<String> words = abbreviationMap.get(abbr);
            // 有相同的 abbr
            // The only word in the data structure that has the same abbreviation is the word itself.
            return words.size() == 1 && words.contains(word);
        }

        private String getAbbreviation(String word) {
            if (word.length() <= 2)
                return word;
            // str = 頭letter + "中間 len"(即 len - 2) + 尾letter
            return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
        }
    }

    // 上邊更短
    class ValidWordAbbr2 {
        StringBuilder sb = new StringBuilder();
        // 只包含唯一縮寫的 abbr: word
        Map<String, String> abbrDictioanry;
        //表示 原str不是 唯一 缩写
        Set<String> nonUniqueAbbr;

        public ValidWordAbbr2(String[] dictionary) {
            nonUniqueAbbr = new HashSet<>();
            abbrDictioanry = new HashMap<>();
            for (String word : dictionary) {
                if (word.length() == 1)
                    continue;
                String abbr = getAbbr(word);
                if (abbrDictioanry.containsKey(abbr)) // 有重复缩写的 原str
                    nonUniqueAbbr.add(abbr);
                else // 有 唯一 缩写的 原str
                    abbrDictioanry.put(abbr, word);
            }
        }

        public boolean isUnique(String word) {
            String abbr = getAbbr(word);
            //只要有重复缩写, false; 且 如果 abbrDictioanry 或 和 输入str相同 就是true.
            return !nonUniqueAbbr.contains(abbr) && (!abbrDictioanry.containsKey(abbr) || abbrDictioanry.get(abbr).equals(word));
        }

        /**
         * 缩写函数
         *
         * @param word
         * @return
         */
        private String getAbbr(String word) {
            if (word.length() == 2) {
                return word;
            }
            sb.setLength(0);
            return sb.append(word.charAt(0))
                    .append(word.length() - 2)
                    .append(word.charAt(word.length() - 1)).toString();
        }
    }
}
