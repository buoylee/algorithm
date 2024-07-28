package cn.buoy.leetcode.array;

public class Q245 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=885UDThAtk0
     * 双指针
     * 思路: 记录 2 word 各自的 "当前的 index", 每次遇到 word1/2 都计算一次距离.
     * 关键: 当 word1 == word2 时, 用 word1Idx 保存 word 的"前一个 index", word2Idx 保存 word 的"当前 index".
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        // 初始值这样设定, 是为了下边, 使 Math.abs(word1Idx - word2Idx) 最大.
        long dist = Integer.MAX_VALUE, word1Idx = dist, word2Idx = -dist;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                word1Idx = i;
            if (words[i].equals(word2)) {
                // 关键: 为了尽量少改动代码, 通过下边2行代码, 使 word2Idx 与 word1Idx 不同, 在 w1 == w2 情况下.
                // 如果 w1 == w2, 用 word1Idx 保存 word 的"前一个 index", word2Idx 保存 word 的"当前 index".
                if (word1.equals(word2))
                    word1Idx = word2Idx;
                word2Idx = i;
            }
            dist = Math.min(dist, Math.abs(word1Idx - word2Idx));
        }
        return (int) dist;
    }
}
