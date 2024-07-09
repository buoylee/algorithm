package cn.buoy.leetcode.string;

public class Q358 {
    /**
     * 簡單, 是能想到的思路
     * https://www.youtube.com/watch?v=28ASDBKFTxw
     * https://www.youtube.com/watch?v=0qF4w4Z4LhE 短
     * 思路: 我們要保證 出現頻率最高的 char 是符合 distance >= k,
     * 通過 charFreq[](char與出現次數), 和 nextValidIdxByChar[](下一次 char 的合法位置邊界), 來選擇下一個 char 加入到 result.
     */
    public String rearrangeString(String str, int k) {
        int len = str.length();
        int[] charFreq = new int[26];
        // 某個 char 的 下一个有效的位置邊界
        int[] nextValidIdxByChar = new int[26];
        // 统计字母频率
        for (int i = 0; i < len; i++)
            charFreq[str.charAt(i) - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (int currIdx = 0; currIdx < len; currIdx++) {
            int charOffsetIdx = findValidMax(charFreq, nextValidIdxByChar, currIdx);
            // 只要出現找不到, 無法組成合法result, 直接返回
            if (charOffsetIdx == -1)
                return "";
            charFreq[charOffsetIdx]--;
            // 更新該 char 下次的合法位置邊界.
            nextValidIdxByChar[charOffsetIdx] = currIdx + k;
            sb.append((char) ('a' + charOffsetIdx));
        }
        return sb.toString();
    }

    // 选出 freq 剩余最多的 char, 且符合 "有效位置邊界" 出現在 "當前index" 之前/== 的 char
    private int findValidMax(int[] charFreq, int[] valid, int currIdx) {
        //最大的 剩余频率
        int max = Integer.MIN_VALUE;
        // char 對應的位置
        int charOffsetIdx = -1;
        for (int i = 0; i < charFreq.length; i++) {
            if (charFreq[i] > 0 && charFreq[i] > max // freq 最高的
                    && valid[i] <= currIdx) { // 符合 "有效位置邊界" 出現在 "當前index" 之前/== 的
                max = charFreq[i];
                charOffsetIdx = i;
            }
        }
        return charOffsetIdx;
    }
}
