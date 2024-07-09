package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q187 {
    /**
     * 简单, 看懂題目的話, 視頻, 代碼
     * https://www.youtube.com/watch?v=z2zF0SzBSHg
     * 思路: 重複就放 result
     */
    public List<String> findRepeatedDnaSequences(String s) {
        Set seen = new HashSet();
        // 因爲 可能重複多次, 所以 result 也要用 set.
        Set result = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            // 重複的就是答案, 放入到答案.
            if (!seen.add(ten))
                result.add(ten);
        }
        return new ArrayList(result);
    }
}
