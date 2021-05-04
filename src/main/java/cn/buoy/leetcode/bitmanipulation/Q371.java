package cn.buoy.leetcode.bitmanipulation;

public class Q371 {
    public static void main(String[] args) {
        Q371 q371 = new Q371();
        int sum = q371.getSum2(-1, 1);
    }

    /**
     * https://www.youtube.com/watch?v=wV-fmKHPCSc
     * 比较巧妙
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            //如果carry还不是0, 还要继续
            //求出所有 需要 进位的位置
            int carry = a & b;
            //如果carry == 0, 不需要再做进位运算的话, 直接把carry位的1加上即可.
            //求出忽略掉进位, 每一位上该显示的1,0.
            a = a ^ b;
            //值算进位后的位置, 每一位carry都要左移.
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
}
