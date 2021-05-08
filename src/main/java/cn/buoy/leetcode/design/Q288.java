package cn.buoy.leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q288 {
    /**
     * 理解题目 才是关键...
     * 如果缩写一样, 且 原str们 与 str 完全相同, 这是true?
     * 但是安代码理解, 好像 只要是有重复的原str 缩写, 都是false
     */
    class ValidWordAbbr {

        StringBuilder sb = new StringBuilder();
        Map<String, String> abbrDictioanry;
        //表示 原str不是 唯一 缩写
        Set<String> nonUniqueAbbr;

        public ValidWordAbbr(String[] dictionary) {
            nonUniqueAbbr = new HashSet<>();
            abbrDictioanry = new HashMap<>();
            for (String word : dictionary) {
                if (word.length() == 1) {
                    continue;
                }
                String abbr = getAbbr(word);
                if (abbrDictioanry.containsKey(abbr)) {
                    //有重复缩写的 原str
                    nonUniqueAbbr.add(abbr);
                } else {
                    // 有 唯一 缩写的 原str
                    abbrDictioanry.put(abbr, word);
                }
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
