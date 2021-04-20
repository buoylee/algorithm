package cn.buoy.leetcode.string;

public class Q125 {
    /**
     * 真简单
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
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
