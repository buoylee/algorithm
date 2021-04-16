package cn.buoy.leetcode.string;

public class Q28 {
    /**
     * https://www.youtube.com/watch?v=OhPtAQtfsuM
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                //整条 needle.len 都匹配, 找到.
                if (j == needle.length()) return i;
                //上方未完成, 又来到了 haystack的最后, 无法向下找, 失败.
                if (i + j == haystack.length()) return -1;
                //中途发现不匹配
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}
