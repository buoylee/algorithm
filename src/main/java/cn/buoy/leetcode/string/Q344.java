package cn.buoy.leetcode.string;

public class Q344 {
    /**
     * 弱智嗎
     * https://www.youtube.com/watch?v=WbMtiB1Ad1w
     * 思路: 頭尾雙指針
     */
    public String reverseString(char[] word) {
        int i = 0;
        int j = word.length - 1;
        while (i < j) {
//            char temp = word[i];
//            word[i] = word[j];
//            word[j] = temp;
            // 有必要這麼寫嗎
            word[i] = (char) (word[i] ^ word[j]);
            word[j] = (char) (word[i] ^ word[j]);
            word[i] = (char) (word[i] ^ word[j]);
            i++;
            j--;
        }
        return new String(word);
    }
}
