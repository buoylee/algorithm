package cn.buoy.leetcode.string;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class Q266 {
    /**
     * 简单, 扫一边, 有重复元素就删除, 遍历完剩下 0, 2 个都正确.
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); ++i) {
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else
                set.remove(s.charAt(i));
        }
        return set.size() == 0 || set.size() == 1;
    }

    // TODO: 2021/4/20
    //不懂
    public boolean canPermutePalindrome2(String s) {
        BitSet bs = new BitSet();
        for (byte b : s.getBytes())
            bs.flip(b);
        return bs.cardinality() < 2;
    }
}
