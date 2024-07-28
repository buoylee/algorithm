package cn.buoy.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q244 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=3noJKN-2a2I
     * 思路: 歸類出 word 的 index list, 然後 比較 給定的 2 word 的 list, 從頭到尾,
     * 关键点, 如果 a(word1[i]) < b(word2[j]), 使 a++ 一步步 靠近 b, 就會得到可能的最小 gap; 當 a >= b 時, b 也和 a 一樣的操作, 直到 a 或 b 有一個到尾.
     */
    class WordDistance {
        // word 和 "word 的 index list"
        HashMap<String, List<Integer>> map;

        public WordDistance(String[] words) {
            this.map = new HashMap<>();
            for (int i = 0; i < words.length; i++)
                map.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
        }

        public int shortest(String word1, String word2) {
            List<Integer> l1 = map.get(word1);
            List<Integer> l2 = map.get(word2);
            int p1 = 0;
            int p2 = 0;
            int result = Integer.MAX_VALUE;
            while (p1 < l1.size() && p2 < l2.size()) {
                result = Math.min(result, Math.abs(l1.get(p1) - l2.get(p2)));
                // 關鍵: 如果 a < b, 使 a++ 一步步 靠近 b, 就會得到可能的最小 gap; 當 a >= b 時, b 也和 a 一樣的操作, 直到 a 或 b 有一個到尾.
                if (l1.get(p1) < l2.get(p2))
                    p1++;
                else
                    p2++;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String[] wordsDict = {"123", "333"};
        Q244 q244 = new Q244();
        WordDistance wordDistance = q244.new WordDistance(wordsDict);
        int shortest = wordDistance.shortest(wordsDict[0], wordsDict[1]);
        System.out.println(shortest);

    }


}

