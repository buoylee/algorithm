package cn.buoy.leetcode.bitmanipulation;

public class Q371 {
    /**
     * 算简单, 視頻
     * https://www.youtube.com/watch?v=wV-fmKHPCSc 失效
     * https://www.youtube.com/watch?v=aDO7qhZDv10
     * 思路: 用 a&b 求出所有 a/b 同時爲 '1' 的位置, 再 <<(左移1位), 现在 '1' 的位置就是 "真正 需要進位的 位置";
     * 用 a^b 求出相加後 '未加進位时的' 的每個位的值,
     * 每次 加完一次 carry, 都有可能在其他bit产生新的 carry. 直到carry爲0.
     */
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        // 在循環中, a爲 a^b 求出相加後'未加進位的'的每個位的值; b爲 1的位置就是需要進位的位數. 直到b(需要進位)爲0時, 完成.
        // 关键: 为什么需要循环, 每次 加完一次 carry, 都有可能在其他bit产生新的 carry.
        while (b != 0) {
            // 求出所有 需要进位的位置 向右偏差一位(因爲是2數同時是1的位置).
            int carry = a & b;
            // 求出 "忽略进位的 每一位上该显示的1/0"
            a = a ^ b;
            // 值算进位后的位置, 每一位carry都要左移.
            b = carry << 1;
        }
        return a;
    }


    public int getSum2(int a, int b) {
        int res = 0;
        int carry = 0;
        int index = 0;
        while (a > 0 || b > 0 || carry != 0) {
            //取最后一位
            int lastA = a & 1;
            int lastB = b & 1;
            int now = 0;
            if (lastA == 0 && lastB == 0) {
                if (carry == 1) {
                    now = 1 << index;
                } else {
                    now = 0 << index;
                }
                carry = 0;
            } else if (lastA == 1 && lastB == 1) {
                if (carry == 1) {
                    now = 1 << index;
                } else {
                    now = 0 << index;
                }
                carry = 1;
            } else {
                if (carry == 1) {
                    now = 0 << index;
                    carry = 1;
                } else {
                    now = 1 << index;
                    carry = 0;
                }
            }
            res |= now;
            a >>= 1;
            b >>= 1;
            index++;
        }

        return res;
    }

    public static void main(String[] args) {
        Q371 q371 = new Q371();
        int sum = q371.getSum2(-1, 1);
    }
}
