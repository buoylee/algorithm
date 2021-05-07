package cn.buoy.leetcode.graph;

public class Q223 {
    /**
     * https://www.youtube.com/watch?v=JVjFriPNiXk
     * 重点: 判断是否重叠, 算重叠面积
     */
    int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //左x 选大的, 右x 选小的, 如果 结果 右比左还大, 则说明x不重叠. 同理y.
        int left = Math.max(A, E), right = Math.min(C, G);
        int bottom = Math.max(B, F), top = Math.min(D, H);

        int res = (C - A) * (D - B) + (G - E) * (H - F);
        if (left < right && bottom < top)
            res -= (right - left) * (top - bottom);
        return res;
    }
}
