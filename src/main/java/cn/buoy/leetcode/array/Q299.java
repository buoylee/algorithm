package cn.buoy.leetcode.array;

public class Q299 {
    /**
     * https://www.youtube.com/watch?v=YmWh-ExoCDw 長
     * https://www.youtube.com/watch?v=miERCUoVk-A 短, 但是 ++ -- 那裏講的讓人覺得是 先++/--, 然後再比.
     * 思路: 重點在如何 算 cows, 只要 freq 是否 一致, 細節看註釋.
     */
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                bulls++;
            } else {
                // 爲什麼這麼寫, 而不寫 count == 0, 因爲沒法判斷 是否第一次遇到.
                // 關鍵: 因爲 secret 遇到則++, guess 遇到則--
                // 所以, 當發現 secret 遍歷出 "某char" 時, 且 count < 0 時(表示 guess 遇到且多個 secret 遇到 "某char"), 這時, 表示出現了相同的char配對, 所以, cows++. 反之亦然.
                if (count[s - '0'] < 0) cows++;
                if (count[g - '0'] > 0) cows++;
                // 檢查完再操作 count
                count[s - '0']++;
                count[g - '0']--;
            }
        }
        return bulls + "A" + cows + "B";
    }

    // 上邊的比較 簡潔.
    public String getHint2(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) bulls++;
            else {
                // 關鍵:
                if (numbers[s] < 0) cows++;
                if (numbers[g] > 0) cows++;
                numbers[s]++;
                numbers[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
