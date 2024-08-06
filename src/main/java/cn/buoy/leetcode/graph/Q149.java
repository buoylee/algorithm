package cn.buoy.leetcode.graph;

import java.util.*;

public class Q149 {
    /**
     * 需要复习?
     * https://www.youtube.com/watch?v=xF2G6DARKBM
     * 思路: 同一點 和 其他點, 組成的線 的斜率, 如果一樣, 說明在一個條直線上.
     * 細節: 爲什麼後邊的起始點不需要看前边的点? 因爲如果前邊的點影響後邊新組成的線上的點的max, 那麼說明, '前邊的點組成的線'其實和現在的點組成的是同一條線. 所以沒有必要統計前邊出現的點.
     */
    public int maxPoints(int[][] points) {
        int result = 1;
        for (int i = 0; i < points.length; i++) {
            // How many points have a perticular slope w.r.t. this point
            // 经过某个点的斜率相同的值.
            // 其他语言可能精度不够, 通过不了, 可以用string((a/gcd)/(b/gcd))方式做key
            Map<Double, Integer> slopesCount = new HashMap<Double, Integer>();
            int dups = 0, max = 0;
            // FIXME: 2021/5/7
            // 为什么可以不用看前边的点?
            // 細節: 反過來想, 如果需要遍歷前邊的點(0-i之間), 而且會影響最後的max, 那麼, 他們的組成的線(之前的點(0-i之間) 和 當前的點), 就會和之前的線重合, 所以其實可以跳過這些點(因爲只有後邊的點和前邊的組成的線不再同一條線上, 才會有更大的max出現).
            for (int j = i + 1; j < points.length; j++) {
                if (i == j) continue;
                // dups
                if (areSamePoints(points[i], points[j])) {
                    dups++;
                    continue;
                }
                double slope = slope(points[i], points[j]);
                slopesCount.put(slope, slopesCount.getOrDefault(slope, 0) + 1);
                // save max no of points with some slope
                max = Math.max(max, slopesCount.get(slope));
            }
            result = Math.max(result, max + dups /*dups are always on same line*/ + 1/*self*/);
        }
        return result;
    }

    // Returns slope of a line. Vertical line slope is undefined and represented as Max value
    // 求斜率
    private double slope(int[] p1, int[] p2) {
        int x1 = p1[0], y1 = p1[1];
        int x2 = p2[0], y2 = p2[1];
        if (x1 == x2) return Double.MAX_VALUE; // vertical line
        if (y1 == y2) return 0; // horizontal line
        return (double) (y2 - y1) / (double) (x2 - x1);
    }

    // Tells if these two points are same
    // 是否同一个点
    private boolean areSamePoints(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }


    /**
     * 求最大公约数
     */
    private int gcd(int x, int y) {
        if (y == 0)
            return x;
        return gcd(y, x % y);
    }

    /**
     * @param points
     * @return
     */
    // FIXME: 2021/5/7
    public int maxPoints2(int[][] points) {
        //小于2点
        if (points == null || points.length < 3)
            return points.length;

        int N = points.length;
        int result = 0, tmp = 2;
        //先选1点, 第2点只能取到倒数第2个点
        for (int i = 0; i < N - 2; i++) {
            int sameWithI = 0;
            //再选1点, 第2点只能取到倒数第1个点
            for (int j = i + 1; j < N - 1; j++) {
                tmp = 2;
                int deltaX = points[j][0] - points[i][0];
                int deltaY = points[j][1] - points[i][1];
                if (deltaX == 0 && deltaY == 0) {
                    sameWithI++;
                    continue;
                }
                for (int k = j + 1; k < N; k++) {
                    //i点 与 第3点k
                    // y/x = y'/x' => y*x' = y'*x
                    int deltaX2 = points[k][0] - points[i][0];
                    int deltaY2 = points[k][1] - points[i][1];
                    if (deltaY * deltaX2 == deltaX * deltaY2)
                        tmp++;
                }
                result = Math.max(result, tmp + sameWithI);
            }
        }
        return result;
    }


}
