package cn.buoy.leetcode.string;

public class Q9 {
    /**
     * 簡單, 迴文
     * https://www.youtube.com/watch?v=WURiBhoms-c
     * 思路:不斷抽取 x 剩餘的最後一位數字, append 到新數 rev 末尾,
     * 當 rev 第一次 >= x 時, 判跳出循環, 如果 相等 => 迴文;
     * 如果 rev > x, rev / 10(去掉最後一位), 看看是不是和 x 相等.
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int rev = 0;
        // 不讀去掉 x 最後一位, append 到 rev
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        // 相等 就是 偶數 迴文,
        // rev去掉最後一位 如果 相等, 奇數 迴文.
        return (x == rev || x == rev / 10);
    }
}
