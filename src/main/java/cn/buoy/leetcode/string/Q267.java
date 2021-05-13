package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q267 {
    /**
     * https://www.youtube.com/watch?v=GOB9i4HOu9U&t=9s
     * 还是算简单的
     */
    List<String> res = new ArrayList();

    public List<String> generatePalindromes(String s) {
        //统计频率
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'a']++;
        }

        //找奇数
        int oddIdx = -1;
        for (int i = 0; i < 26; ++i) {
            if (bucket[i] % 2 == 1) {
                if (oddIdx == -1) oddIdx = i;
                else return res;//如果遇到第2个奇数, 直接返回空
            }
        }


        StringBuilder sb = new StringBuilder();
        //如果有唯一的一个奇数频率, 先插入一个唯一奇数,为中点, 频率--
        if (oddIdx != -1) {
            sb.append((char) (oddIdx + 'a'));
            bucket[oddIdx]--;
        }
        //开始
        dfs(sb, bucket, s.length());
        return res;
    }

    private void dfs(StringBuilder sb, int[] A, int n) {
        if (sb.length() == n) {
            res.add(sb.toString());
            return;
        }

        //想清楚, 这里是没有重复的问题的, 因为 `每个元素` 都各不相同!
        for (int i = 0; i < 26; ++i) {
            if (A[i] > 0) {
                char c = (char) (i + 'a');
                //减2个
                A[i] -= 2;
                //头尾插一个
                sb.append(c);
                sb.insert(0, c);
                dfs(sb, A, n);
                //backtracking
                A[i] += 2;
                sb.deleteCharAt(0);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
