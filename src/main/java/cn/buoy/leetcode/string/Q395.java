package cn.buoy.leetcode.string;

public class Q395 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=GU-03VY12Ic
     * https://www.youtube.com/watch?v=Dn_Wt6lBt4I 短
     * 思路: 从不满足频率的元素的index 切开, 然后 左/右 选大的. 不需要切, 就直接返回 end - start
     */
    public int longestSubstring(String s, int k) {
        return helper(s.toCharArray(), 0, s.length(), k);
    }

    private int helper(char[] str, int start, int end, int k) {
        // 停止条件是字符串的长度小于k
        if (end - start < k)
            return 0;
        int[] charFreq = new int[26];
        // 统计 char freq
        for (int i = start; i < end; i++) {
            int offsetIdx = str[i] - 'a';
            charFreq[offsetIdx]++;
        }
        for (int offsetIdx = 0; offsetIdx < 26; offsetIdx++) {
            // 只切 freq < k 的 char
            if (charFreq[offsetIdx] < k && charFreq[offsetIdx] > 0) {
                // 從 start 到 end, 找到这个字符的位置, 然后分别求它的"左半部分"和"右半部分"
                for (int i = start; i < end; i++) {
                    if (str[i] == offsetIdx + 'a') {
                        int leftMaxLen = helper(str, start, i, k);
                        int rightMaxLen = helper(str, i + 1, end, k);
                        return Math.max(leftMaxLen, rightMaxLen);
                    }
                }
            }
        }
        // 不需要切, 整段都符合, 直接頭尾相減即可.
        return end - start;
    }


    public int longestSubstring2(String s, int k) {
        if (s.length() < k) return 0;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) count[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            // 只從 freq < k 的 index 切開
            if (count[s.charAt(i) - 'a'] >= k) continue;
            int j = i + 1;
            //排除掉有切开元素连一起, 找到下一个不用切的点, 开始到最后, 作为后段开始递归.
            while (j < s.length() && count[s.charAt(j) - 'a'] < k) j++;
            return Math.max(longestSubstring2(s.substring(0, i), k), longestSubstring2(s.substring(j), k));
        }
        return s.length();
    }
}
