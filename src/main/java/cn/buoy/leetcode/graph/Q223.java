package cn.buoy.leetcode.graph;

public class Q223 {
    /**
     * https://www.youtube.com/watch?v=JVjFriPNiXk
     * 簡單, 看視頻, 看圖.
     * 思路: 判断是否重叠, 算重叠面积.
     */
    int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //關鍵: 左x 选大的, 右x 选小的, 如果 结果 右比左还大, 则说明x不重叠. 同理y.
        int left = Math.max(A, E), right = Math.min(C, G);
        // 下選大, 上選小
        int bottom = Math.max(B, F), top = Math.min(D, H);
        // 求2方形各自面積.
        int res = (C - A) * (D - B) + (G - E) * (H - F);
        // 關鍵: 畫圖就很好看出來. 當 最靠右的 左邊線 < 最靠左的 右邊線, 說明 x 軸有重疊. 同理上下邊線. 當同時滿足, 說明有重疊區域, 減去即是答案.
        if (left < right && bottom < top)
            res -= (right - left) * (top - bottom);
        return res;
    }
}
