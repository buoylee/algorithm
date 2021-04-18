package cn.buoy.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Q290 {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        //按顺序找到的string, 保持相同频率就好.
        for (Integer i = 0; i < words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }
}
