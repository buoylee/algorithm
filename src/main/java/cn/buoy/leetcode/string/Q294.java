package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q294 {
    /**
     * https://www.youtube.com/watch?v=rjOTv7dzqC8
     * 记忆化搜素 + 回溯
     *
     * @param s
     * @return
     */
    public boolean canWin(String s) {
        HashMap<String, Boolean> h = new HashMap();
        return canWin(s.toCharArray(), h);
    }

    private boolean canWin(char[] c, HashMap<String, Boolean> h) {
        for (int i = 1; i < c.length; i++)
            if (c[i] == '+' && c[i - 1] == '+') {
                c[i] = '-';
                c[i - 1] = '-';

                boolean t;
                String key = String.valueOf(c);
                if (!h.containsKey(key)) {
                    //关键在这, 整个递归 表示的是, "我下了, 你还能赢吗?"
                    t = canWin(c, h);
                    h.put(key, t);      //System.out.println(key + " --> " + t);
                } else {
                    //保存计算过的status
                    t = h.get(key);     //System.out.println(key + " ==> " + t);
                }   // can not directly use if (t) return true here, cuz you need to restore

                //回溯
                c[i] = '+';
                c[i - 1] = '+';
                if (!t) return true;
            }
        return false;
    }
}
