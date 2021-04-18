package cn.buoy.leetcode.string;

import java.util.*;

public class Q249 {
    /**
     * https://www.youtube.com/watch?v=Im-isYiYqDk
     * 思路是讲 所有的 string 的第一个字母 都转为a开头的序列, 如果转化后的序列相同, 则加入该arr中.
     *
     * @param strings
     * @return
     */
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            //算出原string 与 转化后的 差值.
            int offset = str.charAt(0) - 'a';
            String key = "";
            //求助转化后的序列
            for (int i = 0; i < str.length(); i++) {
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key += c;
            }
            //转化后的值作为map的key
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<String>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        //丢到list返回
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            result.add(list);
        }
        return result;
    }
}
