package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q246 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=ZCYaONcsabY
     * 思路: 反转180, 还是相同的数对.
     */
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('8', '8');
        map.put('1', '1');
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            // left 是否存在与 map
            if (!map.containsKey(num.charAt(left)))
                return false;
            // right 是否匹配 left
            if (map.get(num.charAt(left)) != num.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    // 有点绕, 上边比较直观
    public boolean isStrobogrammatic2(String num) {
        for (int i = 0, j = num.length() - 1; i <= j; i++, j--)
            // 因为 只有可能由2个digit组成, 所以 696 其实是限制了2个数 "69" 和 "96", 奇数中位数, 也只能是"00 11 88"之一.
            if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
                return false;
        return true;
    }
}
