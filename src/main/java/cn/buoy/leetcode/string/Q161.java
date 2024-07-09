package cn.buoy.leetcode.string;

public class Q161 {
    /**
     * 簡單, 視頻, 註釋.
     * https://www.youtube.com/watch?v=nDstaiEUljY
     * 思路: 當檢查到 出現 不同 char 時, 分3類情況, s.charAt(i) 等/大/小于 t.charAt(i), 後續完全相同 / 去掉s這個char, 後續完全相同 / 去掉t這個char, 後續完全相同.
     * 注意: 因爲置遍歷短的str, 所以, 如果 char 都相同, 剩下的 str 的長度 必須只能爲1(不需要管相不相同).
     */
    public boolean isOneEditDistance(String s, String t) {
        // 細節: 遍历短即可, 因爲 substring() 的比較, 和最後 s.length() - t.length() 的比較可以覆蓋.
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            // 出現不同char時, 有3類情況
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1)); // s.length() == t.length() 情況下, 出現了一個不同char, 那剩下的str必須相同.
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1)); // s.length() < t.length() 情況下, 當前出現了一個不同的 char, 除掉 t 這個 char 後, 剩下的str必須相同.
                else // s is longer than t, so the only possibility is deleting one char from s
                    return s.substring(i + 1).equals(t.substring(i)); // s.length() > t.length() 情況下, 當前出現了一個不同的 char, 除掉 s 這個 char 後, 剩下的str必須相同.
            }
        }
        // All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        // 如果 前边所有 char 都相等, 只需要檢查 他們的長度是不是差1即可, 沒必要判斷是否char相同.
        return Math.abs(s.length() - t.length()) == 1;
    }
}
