package cn.buoy.leetcode.array;

public class Q245 {
    /*
    https://www.youtube.com/watch?v=885UDThAtk0
    双指针
    关键: word1 == word2的处理,
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        //使i2 与 i1 不同, 在w1 == w2情况下.
        long dist = Integer.MAX_VALUE, i1 = dist, i2 = -dist;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) //i1 == 1
                i1 = i;
            if (words[i].equals(word2)) {//i2 == 1
                //关键: 为了尽量少改动代码, 通过下边2行代码, 使i2 与 i1 不同, 在w1 == w2情况下.
                //在w1 == w2, 先用i2保存第一个位置, 在第2次遇到相同字母时, 再替换, w2再换上第2次的位置.
                if (word1.equals(word2))
                    i1 = i2;
                i2 = i;
            }
            dist = Math.min(dist, Math.abs(i1 - i2));
        }
        return (int) dist;
    }
}
