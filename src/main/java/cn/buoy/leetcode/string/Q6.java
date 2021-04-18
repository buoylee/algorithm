package cn.buoy.leetcode.string;

public class Q6 {
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        //有几行, 初始化几行
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < sbs.length; i++) sbs[i] = new StringBuilder();
        int idx = 0;
        int direction = -1;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            //先append, 如果到头, 转向, 再++;
            //一开始是0, 向下(++ 1), 到numRows - 1, 向上(++ -1).
            sbs[idx].append(c);
            if (idx == 0 || idx == numRows - 1) direction = 0 - direction;
            idx += direction;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : sbs) res.append(sb);
        return res.toString();
    }
}
