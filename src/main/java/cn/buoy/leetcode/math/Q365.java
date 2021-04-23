package cn.buoy.leetcode.math;

import java.util.*;

public class Q365 {
    /**
     * https://www.bilibili.com/video/BV1R7411m7HL/?spm_id_from=autoNext
     * 优化方案, 排除掉 2边都有水, 但是 +起来 不会超过某个容器的 重复情况.
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {
        // public boolean canMeasureWater(int x, int y, int z) {
        if (z < 0 || z > x + y) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int n = q.poll();
            if (n + x <= x + y && set.add(n + x)) {
                q.offer(n + x);
            }
            if (n + y <= x + y && set.add(n + y)) {
                q.offer(n + y);
            }
            if (n - x >= 0 && set.add(n - x)) {
                q.offer(n - x);
            }
            if (n - y >= 0 && set.add(n - y)) {
                q.offer(n - y);
            }
            if (set.contains(z)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 最初思考方法, 模拟步骤
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater2(int x, int y, int z) {
        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        queue.addLast(new int[]{0, 0});
        seen.add(0 + "," + 0);
        while (queue.size() > 0) {
            int[] currSearch = queue.removeFirst();
            int a = currSearch[0], b = currSearch[1];    // Current water levels of the jars x and y.
            if (a + b == z) return true;

            // Make all next possible moves with the current state of the jugs:
            List<int[]> nextMoves = new ArrayList<>();
            nextMoves.add(new int[]{x, b});     // Fill x.
            nextMoves.add(new int[]{a, y});     // Fill y.
            nextMoves.add(new int[]{0, b});     // Empty x.
            nextMoves.add(new int[]{a, 0});     // Empty y.
            //倒到一边空, 或另一边满.
            nextMoves.add(new int[]{Math.max(0, a - (y - b)), Math.min(a + b, y)});  // Put x into y.
            nextMoves.add(new int[]{Math.min(a + b, x), Math.max(0, b - (x - a))});  // Put y into x.

            for (int[] move : nextMoves) {
                String key = move[0] + "," + move[1];
                if (!seen.contains(key)) {
                    seen.add(key);
                    queue.addLast(move);
                }
            }
        }
        return false;   // There is no way to measure z by using jugs x and y.
    }


    /**
     * 純数
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater3(int x, int y, int z) {
        //limit brought by the statement that water is finallly in one or both buckets
        if (x + y < z) return false;
        //case x or y is zero
        if (x == z || y == z || x + y == z) return true;

        //get GCD, then we can use the property of Bézout's identity
        return z % GCD(x, y) == 0;
    }

    public int GCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
