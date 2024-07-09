package cn.buoy.leetcode.string;

public class Q28 {
    /**
     * 超簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=OhPtAQtfsuM
     * 思路: 找到 和 'needle 第一個 letter' 相同的 'haystack letter', 才會開始檢查 needle 後續 letter.
     */
    public int strStr(String haystack, String needle) {
        // 找 和 needle 匹配 的 第一個字母
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            // 第一個letter相同時, 才會開始比較 needle 後續 letter.
            if (haystack.charAt(i) == needle.charAt(0)) {
                // needle 的index
                int j = 1;
                // 不檢查 i + j < haystack.length() 不會越界嗎?
                while (j < needle.length() || i + j < haystack.length()) {
                    if (haystack.charAt(i + j) != needle.charAt(j))
                        break;
                    j++;
                }
                if (j == needle.length())
                    return i;
            }
        }
        return -1;
    }
}
