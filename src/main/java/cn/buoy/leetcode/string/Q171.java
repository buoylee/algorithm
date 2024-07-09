package cn.buoy.leetcode.string;

public class Q171 {
    /**
     * 簡單, 168題 反轉
     * https://www.youtube.com/watch?v=6QLxVYg5cQQ
     */
    public int titleToNumber(String s) {
        int result = 0;
        //注意 s.charAt(i) - 'A' 是相對位置, 需要 +1
        for (int i = 0; i < s.length(); i++)
            result = result * 26 + (s.charAt(i) - 'A' + 1);
        return result;
    }
}
