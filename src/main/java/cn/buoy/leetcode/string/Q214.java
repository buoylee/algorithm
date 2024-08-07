package cn.buoy.leetcode.string;

public class Q214 {
    /**
     * https://www.youtube.com/watch?v=aQ-Y51n6SD0
     * 思路:
     * 例如: a a b c a d
     * 1. 檢查 本身是不是迴文.
     * 2. 檢查 head 只加 1個字母能不能構成 迴文.   x (a a b c  a) d
     * 3. 檢查 head 只加 2個字母能不能構成 迴文. y x (a a b c) a  d
     * ...
     * 所以, 能看出, 就是不斷檢查 "()" 內 str, 是否是 迴文,
     * 如果都不是迴文, 最後會在 第一個 'a' 作爲中點組成的 迴文.  x4 x3 x2 x1 (a) a b c a d;  x1-x4 就是 第一個 'a' 後邊的 char.
     */
    public String shortestPalindrome2(String s) {
        int left = 0, right = s.length() - 1;
        // 表示 迴文 末尾 可能的index. 這個點之後, 就是需要補元素在 s 頭部的 rev(str).
        // 例如 [a a(這個就是 paliEnd) b c], 我們只需要補 c b 在頭部, 即 [c b a a b c]
        int paliEnd = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                left = 0;
                paliEnd--;
                right = paliEnd;
            }
        }
        return new StringBuilder(s.substring(paliEnd + 1)).reverse().toString() + s;
    }

    /**
     * 有空來看
     * KMP算法:
     * https://www.youtube.com/watch?v=dgPabAsTFa8&t=915s
     * https://www.youtube.com/watch?v=3IFxpozBs2I
     * 原理: 利用 KMP中 求 `匹配 前后缀 最大长度`, 将reverse str 放在原str后方, 又因为reverse str 的后缀 如果是回文的话, 就会和 原str 前缀 相同,
     * 所以, 最后我们将问题转为, 利用求DMP table, 来找出 和reverse str拼接的str 求出 结果.
     * https://www.youtube.com/watch?v=c4akpqTwE5g
     * 详细解析:
     * https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation
     */
    public String shortestPalindrome(String s) {
        //不加#, 最长会超过原str长度, 因为组合后的str是镜像关系, 会延伸到最后.
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);

        //get the maximum palin part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s) {
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part

        int index = 0;
        //build KMP table
        //skip index 0, we will not match a string with itself
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(index) == s.charAt(i)) {
                //we can extend match in prefix and postfix
                table[i] = table[i - 1] + 1;
                index++;
            } else {
                //match failed, we try to match a shorter substring

                //by assigning index to table[i-1], we will shorten the match string length, and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i - 1];

                while (index > 0 && s.charAt(index) != s.charAt(i)) {
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index - 1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if (s.charAt(index) == s.charAt(i)) {
                    //if match, then extend one char
                    index++;
                }

                table[i] = index;
            }

        }

        return table;
    }
}
