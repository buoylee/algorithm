package cn.buoy.leetcode.string;

public class Q246 {
    /**
     * https://www.youtube.com/watch?v=ZCYaONcsabY
     *
     * @param num
     * @return
     */
    public boolean isStrobogrammatic(String num) {
        for (int i = 0, j = num.length() - 1; i <= j; i++, j--)
            // 69 96 -> 696, 中位数1个, 只能出现相同的, 满足!
            if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
                return false;
        return true;
    }
}
