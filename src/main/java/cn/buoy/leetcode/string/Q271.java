package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q271 {
    class Codec {
        /**
         * https://www.youtube.com/watch?v=pYZnxzaVQrY
         *
         * @param strs
         * @return
         */
        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            //注意这就好
            //{ 字符长度 + "/" + str }...
            for (String s : strs) {
                sb.append(s.length()).append('/').append(s);
            }
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> ret = new ArrayList<String>();
            int i = 0;
            //解码
            while (i < s.length()) {
                int slash = s.indexOf('/', i);
                int size = Integer.valueOf(s.substring(i, slash));
                i = slash + size + 1;
                ret.add(s.substring(slash + 1, i));
            }
            return ret;
        }
    }
}


