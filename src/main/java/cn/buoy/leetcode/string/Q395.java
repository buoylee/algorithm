package cn.buoy.leetcode.string;

public class Q395 {
    /**
     * https://www.youtube.com/watch?v=GU-03VY12Ic
     * 思路就是, 从不满足频率的元素的index 切开, 然后选最大. 本来自己的选最大的思路是从头找, 遇到 切开元素 就统计 之前的长度, 知道最后, 但好像没人这么写, 算了. 就用递归吧
     */
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) count[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            //这里 i就就是切开元素, 这里前边的一段开始递归
            if (count[s.charAt(i) - 'a'] >= k) continue;
            int j = i + 1;
            //排除掉有切开元素连一起, 找到下一个不用切的点, 开始到最后, 作为后段开始递归.
            while (j < s.length() && count[s.charAt(j) - 'a'] < k) j++;
            return Math.max(longestSubstring(s.substring(0, i), k), longestSubstring(s.substring(j), k));
        }
        return s.length();
    }
}
