package cn.buoy.leetcode.matrix;

public class Q370 {
    /**
     * 有特殊方法, 視頻, 代碼簡單.
     * https://www.youtube.com/watch?v=b9gMT2sqeJI
     * https://www.youtube.com/watch?v=9sMupCm0kdg 短, 好
     * 原理: 只需要 在head += value(进入value区域),
     * 然后 在end+1 -= value(表示离开value区域),
     * 中间的0 都可以用前一个index 的值 += 预处理的值.
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int value = update[2];
            //只在start += value
            res[start] += value;
            //如果end 没到末尾index, 则在end+1初 减去 value
            if (end < length - 1)
                res[end + 1] -= value;
            //其他都置0, 不用處理.
        }
        for (int i = 1; i < length; i++)
            res[i] += res[i - 1];
        return res;
    }
}
