package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q30 {

    /**
     * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-6/
     * 第2个方法, 这个时间很强
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        final Map<String, Integer> need = new HashMap<>();
        for (final String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }
        final int n = s.length();
        final int num = words.length;
        final int len = words[0].length();
        final List<Integer> ans = new ArrayList<>();
        //用每个单词的长度来分
        for (int i = 0; i < len; i++) {
            int l = i, count = 0;
            final Map<String, Integer> seen = new HashMap<>();
            //直到
            for (int j = i; j <= n - len; j += len) {
                final String word = s.substring(j, j + len);
                if (need.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    //如果有需要的单词, 且还不够, 次数++
                    if (seen.get(word) <= need.get(word)) {
                        count++;
                    } else {
                        //有多余的单词
                        while (seen.get(word) > need.get(word)) {
                            //拿到窗口最左边的单词, 移除
                            final String first = s.substring(l, l += len);
                            seen.put(first, seen.getOrDefault(first, 0) - 1);
                            //如果first 单词变得不满足了, 需要update count
                            if (seen.get(first) < need.getOrDefault(first, 0)) {
                                count--;
                            }
                        }
                    }
                    //满足, 记录. 这里的count 已经是判断过的, 不会包含多余的单词
                    //然后在移除头部单词, 为了向后检查条件.(?)
                    if (count == num) {
                        ans.add(l);
//                        count--;
//                        final String first = s.substring(l, l += len);
//                        seen.put(first, seen.get(first) - 1);
                    }
                } else {
                    //如果进来了不需要的单词, 跳过一个单词继续.
                    seen.clear();
                    count = 0;
                    l = j + len;
                }
            }
        }
        return ans;
    }

    /**
     * https://www.youtube.com/watch?v=n9fYwG3dC_Q
     */
    // TODO: 2021/4/20

    /**
     * https://www.youtube.com/watch?v=eebtefMRvjQ
     */
    // TODO: 2021/4/20
}
