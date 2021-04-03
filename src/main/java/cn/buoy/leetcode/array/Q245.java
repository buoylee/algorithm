package cn.buoy.leetcode.array;

public class Q245 {
    /*
    双指针
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        //使i2 与 i1 不同, 在w1 == w2情况下.
        long dist = Integer.MAX_VALUE, i1 = dist, i2 = -dist;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) //i1 == 1
                i1 = i;
            if (words[i].equals(word2)) {//i2 == 1
                //使i2 与 i1 不同, 在w1 == w2情况下.
                if (word1.equals(word2))
                    i1 = i2;
                i2 = i;
            }
            dist = Math.min(dist, Math.abs(i1 - i2));
        }
        return (int) dist;
    }
}
