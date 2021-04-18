package cn.buoy.leetcode.string;

public class Q344 {
    /**
     * 简单到失智
     */
//    public String reverseString(String s) {
    public String reverseString(char[] word) {
//        char[] word = s.toCharArray();

        int i = 0;
        int j = word.length - 1;
        while (i < j) {
//            char temp = word[i];
//            word[i] = word[j];
//            word[j] = temp;
            word[i] = (char) (word[i] ^ word[j]);
            word[j] = (char) (word[i] ^ word[j]);
            word[i] = (char) (word[i] ^ word[j]);
            i++;
            j--;
        }
        return new String(word);
    }
}
