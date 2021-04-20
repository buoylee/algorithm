package cn.buoy.leetcode.string;

public class Q9 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int rev = 0;
        //使rev从 个位 到 高位 排列, 直到rev > x, 即超过一半位置, 说明 过了中位数, 这个时候 有2种情况, 奇偶.
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        //相等时偶, 或 rev去掉中位数 看是否相等.
        return (x == rev || x == rev / 10);
    }
}
