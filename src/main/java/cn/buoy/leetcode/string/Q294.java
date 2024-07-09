package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q294 {
    HashMap<String, Boolean> memo = new HashMap();

    /**
     * 類似 293
     * https://www.youtube.com/watch?v=rjOTv7dzqC8
     * https://www.youtube.com/watch?v=RHK53uZjLn8 短
     * 思路: 记忆化搜素 + 回溯.
     * 模擬遊戲走法, 且在對方走的時候輸, 我們才能贏.
     */
    public boolean canWin(String s) {
        if (memo.containsKey(s))
            return memo.get(s);
        for (int i = 0; i < s.length() - 1; i++) {
            // i - 1 和 i 都是 + 才能 變成 "--"
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                // 拼新的 str, 不用回溯
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                // 关键: 整个递归 表示的是, "我下了, 你还能赢吗?"; 只有對方下的時候輸, 我們才贏; memo
                if (!canWin(opponent)) {
                    memo.put(s, true);
                    return true;
                }
            }
        }
        // for 找完都沒有, false; memo
        memo.put(s, false);
        return false;
    }

    // 不夠簡潔
    public boolean canWin2(String s) {
        return canWin2(s.toCharArray(), memo);
    }

    private boolean canWin2(char[] charArr, HashMap<String, Boolean> strToWin) {
        for (int i = 1; i < charArr.length; i++)
            // i - 1 和 i 都是 + 才能 變成 "--"
            if (charArr[i] == '+' && charArr[i - 1] == '+') {
                charArr[i] = '-';
                charArr[i - 1] = '-';
                boolean Won;
                String str = String.valueOf(charArr);
                if (!strToWin.containsKey(str)) {
                    //关键在这, 整个递归 表示的是, "我下了, 你还能赢吗?"
                    Won = canWin2(charArr, strToWin);
                    strToWin.put(str, Won);      //System.out.println(str + " --> " + Won);
                } else
                    // 記憶化, 保存计算过的status
                    // can not directly use if (Won) return true here, cuz you need to restore
                    Won = strToWin.get(str);     //System.out.println(str + " ==> " + Won);
                //回溯
                charArr[i] = '+';
                charArr[i - 1] = '+';
                if (!Won) return true;
            }
        return false;
    }

    // 沒有記憶化
    public boolean canWin3(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                char[] arr = s.toCharArray();
                arr[i] = '-';
                arr[i - 1] = '-';
                // 下一步由對手走, 所以, 對手不能贏.
                if (!canWin3(new String(arr))) {
                    return true;
                }
            }
        }
        return false;
    }
}
