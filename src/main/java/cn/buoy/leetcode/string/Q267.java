package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q267 {
    /**
     * 簡單, 視頻, 註釋
     * 266題 相關
     * https://www.youtube.com/watch?v=GOB9i4HOu9U&t=9s
     * https://www.youtube.com/watch?v=Fvx65X4fBUk 短
     * 思路: 和 266題 一樣, 先判斷 能否構成迴文.
     * 如果是奇數, 先把它作爲 中點 加入到 substr,
     * 然後用回溯 從里到外 構建迴文.
     */
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList();
        StringBuilder temp = new StringBuilder();
        // 统计频率
        int[] letterFreqArr = new int[26];
        for (char c : s.toCharArray())
            letterFreqArr[c - 'a']++;
        // 找奇数, 且如果發現 多於1個 奇數 char, 直接返回空結果
        int oddIdx = -1;
        for (int i = 0; i < 26; ++i) {
            if (letterFreqArr[i] % 2 == 1) {
                // 不是 -1 的話, 說明 已經遇到過 奇數.
                if (oddIdx == -1)
                    oddIdx = i;
                else
                    return res; // 如果遇到第2个奇数, 直接返回空
            }
        }
        //如果 有唯一一个 奇数频率, 先插入一个唯一奇数, 作为中点, 频率--
        if (oddIdx != -1) {
            temp.append((char) (oddIdx + 'a'));
            letterFreqArr[oddIdx]--;
        }
        // 從 頭尾2邊 添加相同 char
        dfs(res, temp, letterFreqArr, s.length());
        return res;
    }

    /**
     * @param result
     * @param temp        臨時 substr
     * @param charFreqArr char 和 "char 的 freq"
     * @param len         str 的目標長度
     */
    private void dfs(List<String> result, StringBuilder temp, int[] charFreqArr, int len) {
        if (temp.length() == len) {
            result.add(temp.toString());
            return;
        }
        //想清楚, 这里是没有重复的问题的, 因为 `每个元素` 都各不相同!
        for (int i = 0; i < 26; ++i) {
            if (charFreqArr[i] > 0) {
                char c = (char) (i + 'a');
                //减2个
                charFreqArr[i] -= 2;
                //头尾插一个
                temp.insert(0, c);
                temp.append(c);
                dfs(result, temp, charFreqArr, len);
                //backtracking
                charFreqArr[i] += 2;
                temp.deleteCharAt(0);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
}
