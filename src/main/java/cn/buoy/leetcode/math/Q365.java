package cn.buoy.leetcode.math;

import java.util.*;

public class Q365 {
    /**
     * https://www.youtube.com/watch?v=XbkZrQn8WMQ 參考用吧, 當瞭解題目. 感覺目前視頻都沒有一個講的全的(某些+-情況沒說明),
     * 看註釋和代碼.
     * 容易理解的解法. bfs, 模拟所有步骤.
     */
    public boolean canMeasureWater2(int capacity1, int capacity2, int target) {
        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> used = new HashSet<>();
        queue.addLast(new int[]{0, 0});
        used.add(0 + "," + 0);
        while (queue.size() > 0) {
            int[] currSearch = queue.removeFirst();
            int level1 = currSearch[0], level2 = currSearch[1];    // Current water levels of the jars x and y.
            if (level1 + level2 == target) return true;
            // Make all next possible moves with the current state of the jugs:
            // 操作完這一輪, 有可能出現的狀態. 一一列出.
            List<int[]> nextMoves = new ArrayList<>();
            nextMoves.add(new int[]{capacity1, level2});     // Fill x.
            nextMoves.add(new int[]{level1, capacity2});     // Fill y.
            nextMoves.add(new int[]{0, level2});     // Empty x.
            nextMoves.add(new int[]{level1, 0});     // Empty y.
            //倒到一边空, 或另一边满.
            nextMoves.add(new int[]{Math.max(0, level1 - (capacity2 - level2)), Math.min(level1 + level2, capacity2)});  // Put x into y.
            nextMoves.add(new int[]{Math.min(level1 + level2, capacity1), Math.max(0, level2 - (capacity1 - level1))});  // Put y into x.
            // 如果出現過的狀態, 將不再做一下輪操作(排除並不入隊).
            for (int[] move : nextMoves) {
                String key = move[0] + "," + move[1];
                if (!used.contains(key)) {
                    used.add(key);
                    queue.addLast(move);
                }
            }
        }
        return false;   // There is no way to measure target by using jugs bottle1 and bottle2.
    }

    /**
     * https://www.bilibili.com/video/BV1R7411m7HL/?spm_id_from=autoNext
     * 關鍵是 如何理解, 只有 4種操作,
     * 難道 例如: 把 a倒滿, 然後 a 倒入 b 直至 b滿, 然後把 a剩餘的倒掉. 這樣的例子可以解釋爲, 由2步(-a, +b) 組合而成嗎?
     * 优化方案, 排除掉 2边都有水, 但是 +起来 不会超过某个容器的 重复情况.
     */
    public boolean canMeasureWater(int a, int b, int target) {
        if (target < 0 || target > a + b) {
            return false;
        }
        Set<Integer> used = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node + a <= a + b && used.add(node + a)) {
                queue.offer(node + a);
            }
            if (node + b <= a + b && used.add(node + b)) {
                queue.offer(node + b);
            }
            if (node - a >= 0 && used.add(node - a)) {
                queue.offer(node - a);
            }
            if (node - b >= 0 && used.add(node - b)) {
                queue.offer(node - b);
            }
            if (used.contains(target)) {
                return true;
            }
        }
        return false;
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
