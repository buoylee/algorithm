package cn.buoy.leetcode.string;

public class Q125 {
    /**
     * 太簡單, 視頻
     * https://www.youtube.com/watch?v=9-6B2-aTSh0
     * 思路: 首尾指針
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 跳過 非 LetterOrDigit
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            // 檢查完當前char, 記得左++ 右--
            left++;
            right--;
        }
        return true;
    }


    /**
     * 不依赖api
     */
    private static final char[] charMap = new char[256];

    //设置 非数字和字母 value都是0
    static {
        for (int i = 0; i < 10; i++) {
            charMap[i + '0'] = (char) (1 + i);  // numeric
        }
        for (int i = 0; i < 26; i++) {
            charMap[i + 'a'] = charMap[i + 'A'] = (char) (11 + i);  //alphabetic, ignore cases
        }
    }

    public boolean isPalindrome2(String s) {
        char[] pChars = s.toCharArray();
        int start = 0, end = pChars.length - 1;
        char cS, cE;
        while (start < end) {
            cS = charMap[pChars[start]];
            cE = charMap[pChars[end]];
            if (cS != 0 && cE != 0) {
                if (cS != cE) return false;
                start++;
                end--;
            } else {
                //跳过 非数字和字母
                if (cS == 0) start++;
                if (cE == 0) end--;
            }
        }
        return true;
    }
}
