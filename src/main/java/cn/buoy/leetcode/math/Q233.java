package cn.buoy.leetcode.math;

public class Q233 {
    /**
     * 很繞, 不用看視頻, 直接看鏈接第2解, 看註釋. 仔細就能看懂. 注意, 關鍵在於, 如何排列組合, 要想清楚. 代碼簡單.
     * https://leetcode-cn.com/problems/number-of-digit-one/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-50/
     * 思路: 总体思想就是分类，先求所有数中个位是 1 的个数，再求十位是 1 的个数，再求百位是 1 的个数...
     * 假设 n = xyzdabc，此时我们求千位是 1 的个数，也就是 d 所在的位置。
     * 那么此时有三种情况，
     * d == 0，那么千位上 1 的个数就是 xyz * 1000
     * 同樣, 0 ~ xyz-1 => xyz * 1000;
     * 因爲需要退位, 原數 xyz0 這個preDigits的組合 abc 無法到達, 全部丟棄, 剩下的 'abc' 就是 可以任選的 '000~999', 包含在 xyz * 1000 中.
     * <p>
     * d == 1，那么千位上 1 的个数就是 xyz * 1000 + abc + 1
     * 同樣, 0 ~ xyz-1 => xyz * 1000;
     * abc + 1, 表示的是 xyz1 這個preDigits的組合前提下的可能性, 0~abc.
     * <p>
     * d > 1，那么千位上 1 的个数就是 xyz * 1000 + 1000
     * 同樣, 0 ~ xyz-1 => xyz * 1000;
     * 1000, 因爲 d > 1, 當我們把 d 定在1的範圍內時, abc可以取 0~999
     */
    public int countDigitOne(int num) {
        int count = 0;
        //依次考虑个位、十位、百位...是 1 的數量.
        //base = 1000, 对应于上边举的例子
        for (int base = 1; base <= num; base *= 10) {
            // 當前digit 的后綴 number
            int post = num % base;
            // 包含當前digit的 前綴 number
            int preAndCurr = num / base;
            // 當前digit
            int currDigit = preAndCurr % 10;
            // + 8 优化, 减少 d>1 的判断, 0,1 加了也不会进位, 大于等于2的 则会使 pre +1.
            // 當前digit的 前綴 number
            int pre = preAndCurr / 10;
            // 統一處理, 共同部分
            count += pre * base;
            // 下邊2個if, 看上邊註釋寫的原理.
            if (currDigit > 1)
                count += base;
            if (currDigit == 1)
                count += post + 1;
            // 已檢查完 num 頭部digit, 退出.
            if (pre == 0)
                break;
        }
        return count;
    }

    public static void main(String[] args) {
        Q233 q233 = new Q233();
        int i = q233.countDigitOne(2147483647);
        System.out.println(i);
    }
}
