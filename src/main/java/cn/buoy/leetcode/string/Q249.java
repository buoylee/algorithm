package cn.buoy.leetcode.string;

import java.util.*;

public class Q249 {
    /**
     * 简单, 视频, 注释
     * https://www.youtube.com/watch?v=Im-isYiYqDk
     * 思路: 所有的原 str 都统一转为 "a 开头" 的相同偏移的 str, 如果转化后 str 相同的话, 则加入该分组中.
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            // 转统一化成 "a 开头" 的相同偏移的 str
            String shiftedKey = getShiftedKey(str);
            map.putIfAbsent(shiftedKey, new ArrayList<>());
            // 转化后的值作为map的key
            map.get(shiftedKey).add(str);
        }
        //map.values 放入 list 返回
        return new ArrayList<>(map.values());
    }

    /**
     * 转化后的 str 用作 map 的 key
     */
    private String getShiftedKey(String str) {
        char[] chars = str.toCharArray();
        StringBuilder key = new StringBuilder();
        // 计算出以 str 第一个 letter 相对于 'a' 的偏移. 转化 后续所有 letter
        int offset = chars[0] - 'a';
        // 转化 str 的所有 char
        for (char c : chars) {
            char shiftedChar = (char) (c - offset);
            // 例如: 原 char ==  'b', 需要偏移 -5, 那 shiftedChar 就是 负数了, 需要转为 合法的 letter char(+= 26)(等于 a~z 转了一圈).
            if (shiftedChar < 'a')
                shiftedChar += 26;
            key.append(shiftedChar);
        }
        return key.toString();
    }
}
