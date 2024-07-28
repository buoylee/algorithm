package cn.buoy.leetcode.array;

public class Q243 {
    /**
     * 簡單, 視頻.
     * https://www.youtube.com/watch?v=HmBjC413Vug
     * 思路: 遍歷 words, 一旦出現 word1/word2, 他们的 index 賦值給對應的 p1/p2,
     * 只要 word1/2 都存在, 就計算距離.
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = -1;
        int p2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                p1 = i;
            else if (words[i].equals(word2))
                p2 = i;
            if (p1 != -1 && p2 != -1)
                min = Math.min(min, Math.abs(p1 - p2));
        }
        return min;
    }

    // 上邊的更直觀
    public int shortestDistance2(String[] words, String word1, String word2) {
        int index = -1, minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            //找到2关键字之一,则标记
            if (words[i].equals(word1) || words[i].equals(word2)) {
                //如果2 指针不同值 则计算最小值
                if (index != -1 && !words[index].equals(words[i])) {
                    minDistance = Math.min(minDistance, i - index);
                }
                index = i;
            }
        }
        return minDistance;
    }
}
