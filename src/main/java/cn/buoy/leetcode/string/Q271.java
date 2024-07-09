package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q271 {
    class Codec {
        /**
         * 簡單, 視頻, 代碼
         * https://www.youtube.com/watch?v=pYZnxzaVQrY
         * https://www.youtube.com/watch?v=ylzMfyycng8 短
         * 思路: 用 substr.len + "/" + substr 來 表示 一個元素,
         * <p>
         * 細節: 如果 substr末尾 出現 digit, 並不會影響編碼,
         * 因爲 編碼後的 str, 頭部已經 切出 確定的 substr長度,
         * 所以, 下一次的 '/' 前 str, 就是 '/' 後 str 的 len
         */
        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            //關鍵: 字符长度 + "/" + str ...
            for (String s : strs)
                sb.append(s.length()).append('/').append(s);
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> result = new ArrayList<String>();
            int i = 0;
            while (i < s.length()) {
                // indexOf('/', i) 表示 從index == i開始找 '/'
                int slashIdx = s.indexOf('/', i);
                int size = Integer.valueOf(s.substring(i, slashIdx));
                // 下一個 slashIdx 開始找的位置
                i = slashIdx + size + 1;
                result.add(s.substring(slashIdx + 1, i));
            }
            return result;
        }
    }
}


