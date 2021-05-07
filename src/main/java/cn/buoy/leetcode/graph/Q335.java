package cn.buoy.leetcode.graph;

public class Q335 {
    /**
     * https://leetcode.com/problems/self-crossing/discuss/729133/How-to-explain-to-interviewer-335.-Self-Crossing
     * https://www.youtube.com/watch?v=W7MyjXDE5xg
     * 不好理解
     */
    public boolean isSelfCrossing2(int[] x) {
        if (x.length <= 3) {
            return false;
        }
        int i = 2;
        // keep spiraling outward
        while (i < x.length && x[i] > x[i - 2]) {
            i++;
        }
        if (i >= x.length) {
            return false;
        }
        // transition from spiraling outward to spiraling inward
        //这是 边递减 开始的第一步.
        //这个是等于或超过了 x[i - 2] - x[i - 4], i+1就要受到 x[i-1] - x[i-1] 的限制.
        if ((i >= 4 && x[i] >= x[i - 2] - x[i - 4]) ||
                //这里 没有 i - 4, 如果不好想就写 >=
                (i == 3 && x[i] == x[i - 2])) {
            //update i-1 的限制
            x[i - 1] -= x[i - 3];
        }
        i++;
        // keep spiraling inward
        while (i < x.length) {
            if (x[i] >= x[i - 2]) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean isSelfCrossing(int[] x) {
        for (int i = 3; i < x.length; i++) {
            //一直递减 相遇 (里边叉出去)
            if (i >= 3 && x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) return true;
            //相等 相遇
            if (i >= 4 && x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2]) return true;
            //由大变小 相遇 (外边插进来)
            if (i >= 5 && x[i - 2] >= x[i - 4] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 1] <= x[i - 3] && x[i - 4] + x[i] >= x[i - 2])
                return true;
        }

        return false;
    }
}
