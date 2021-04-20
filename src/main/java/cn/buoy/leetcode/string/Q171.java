package cn.buoy.leetcode.string;

public class Q171 {
    /**
     * https://www.youtube.com/watch?v=6QLxVYg5cQQ
     *
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int result = 0;
        //注意 加1 就好. A 要当 1 看.
        for (int i = 0; i < s.length(); result = result * 26 + (s.charAt(i) - 'A' + 1), i++) ;
        return result;
    }
}
