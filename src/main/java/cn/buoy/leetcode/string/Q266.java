package cn.buoy.leetcode.string;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class Q266 {
    /**
     * 简单, 快速视频, 代碼; 寫法不同, 思路一樣.
     * https://www.youtube.com/watch?v=ioup93p2iGo
     * 思路: 奇數個 char "出現次數", 有且只能出現一次.
     * 迴文 有 奇偶2種情況,
     * 奇: 除了有 1個 char 的 "出現次數" 是奇數的, 其他 char 的 "出現次數" 必須是偶數(也可能, 這個迴文只有 1個 char)
     * 偶: 各自的 char 的 "出現次數" 必須是 偶數.
     */
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); ++i) {
            // 數到 偶數 就 set.add
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else // 數到 偶數 就 set.remove
                set.remove(s.charAt(i));
        }
        // 全是偶數 || 奇數個 char "出現次數", 有且只能出現一次.
        return set.size() == 0 || set.size() == 1;
    }

    // 思路一樣, 用 byte 表示 char, BitSet.flip 來統計 奇偶. api有空了解一下
    public boolean canPermutePalindrome2(String s) {
        BitSet bs = new BitSet();
        for (byte b : s.getBytes())
            // 0 => 1 || 1 => 0
            bs.flip(b);
        // 有 2個 1(奇數).
        return bs.cardinality() < 2;
    }
}
