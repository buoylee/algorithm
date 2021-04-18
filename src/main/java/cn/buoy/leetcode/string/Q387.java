package cn.buoy.leetcode.string;

public class Q387 {
    /**
     * 简单
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int freq[] = new int[26];
        //第一次统计字母频率
        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;
        //第2次找出第一个出现频率1次的
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}
