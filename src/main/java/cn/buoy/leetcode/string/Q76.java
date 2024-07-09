package cn.buoy.leetcode.string;

public class Q76 {
    /**
     * 簡單, 代碼不太好寫, 視頻, 代碼
     * https://www.youtube.com/watch?v=63i802XLgOM basketwangCoding
     * https://www.youtube.com/watch?v=dzdtzymjM7A 短
     * 思路: 左右指針, 當滿足 availableNum == 0 時, 移動左指針; 當 availableNum > 0 時, 移動右指針.
     */
    public String minWindow(String s, String t) {
        int[] letterFreqMap = new int[128];
        for (char c : t.toCharArray())
            letterFreqMap[c]++;
        int left = 0, right = 0, minLen = Integer.MAX_VALUE,
                minStart = 0, // 只有在出現 最短長度 時, 才會更新 start, 用於 result 返回 str.
                availableNum = t.length(); // availableNum 表示 短string 的 char 可用的个数.
        while (right < s.length()) {
            final char rightLetter = s.charAt(right);
            if (letterFreqMap[rightLetter] > 0)
                availableNum--;
            letterFreqMap[rightLetter]--;
            right++;
            //一旦满足, 开始从 left 减少, 直到再次不满足 availableNum == 0.
            while (availableNum == 0) {
                // 出現 更小len 時, 更新 minLen, minStart
                if (minLen > right - left) {
                    minLen = right - left;
                    minStart = left;
                }
                final char leftLetter = s.charAt(left);
                // 还回 letterFreqMap
                letterFreqMap[leftLetter]++;
                // letterFreqMap[leftLetter] 可以出現 負數, 因爲 longStr 是可以出現超過 短str 某个char 的次數,
                // 所以, 只有在 letterFreqMap[leftLetter] 出现了正数的时候, 才需要 availableNum++
                if (letterFreqMap[leftLetter] > 0)
                    availableNum++;
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}

