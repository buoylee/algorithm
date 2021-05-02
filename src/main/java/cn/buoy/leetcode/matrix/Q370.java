package cn.buoy.leetcode.matrix;

public class Q370 {
    /**
     * https://www.youtube.com/watch?v=b9gMT2sqeJI
     * 好屌! 原理是 只需要 在head += value(进入value区域), 然后 在end+1 -= value(表示离开value区域), 中间的0 都可以用前一个index 的值 += 预处理的值, 就是最后答案.
     */
    public int[] getModifiedArray(int length, int[][] updates) {

        int[] res = new int[length];
        for (int[] update : updates) {
            int value = update[2];
            int start = update[0];
            int end = update[1];

            //只在start += value
            res[start] += value;

            //如果end 没到末尾index, 则在end+1初 减去 value
            if (end < length - 1)
                res[end + 1] -= value;
            //其他都置0

        }

        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }

        return res;
    }
}
