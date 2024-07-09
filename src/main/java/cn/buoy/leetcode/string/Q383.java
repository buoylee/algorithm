package cn.buoy.leetcode.string;

public class Q383 {
    /**
     * 弱智
     * https://www.youtube.com/watch?v=597NeXkyfOQ
     * 思路: 先統計 magazine 中 char 的出現次數, 一旦發現 ransomNote 有 char 的次數 > magazine, false.
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        //杂志字母全入, 统计频率
        for (int i = 0; i < magazine.length(); i++)
            arr[magazine.charAt(i) - 'a']++;
        //遍历信, 减去, 知道出现 -1
        for (int i = 0; i < ransomNote.length(); i++)
            if (--arr[ransomNote.charAt(i) - 'a'] < 0)
                return false;
        return true;
    }
}
