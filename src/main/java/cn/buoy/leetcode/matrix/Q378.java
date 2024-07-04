package cn.buoy.leetcode.matrix;

import java.util.PriorityQueue;

public class Q378 {
    /**
     * 背後原理, 看下邊註釋, 再看這個代碼.
     * https://www.youtube.com/watch?v=-BJeWxPV8qg
     * 原始思路在下邊, 不知道爲什麼想到可以優化成這樣? 很巧妙.
     * PriorityQueue 幫我們選出了 當前最小, 然後向有擴展. 很巧妙的先把向下擴展到最大, 然後再向右擴展.
     */
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 首列push入queue.
        for (int i = 0; i < len; i++)
            // 爲了接下來 向右擴展, 保存了 座標.
            pq.offer(new int[]{matrix[i][0], i, 0});
        // 0開頭, 不到 k-1, 所以 實際次數爲 k-1, 最後一個(第k個)留給返回結果時彈出.
        for (int i = 0; i < k - 1; i++) {
            // 彈出一個
            int[] curr = pq.poll();
            int currRow = curr[1], currCol = curr[2];
            // 下一個 不能超過 範圍.
            if (currCol + 1 < len)
                // 對最小的 node 向右擴展.
                pq.offer(new int[]{matrix[currRow][currCol + 1], currRow, currCol + 1});
        }
        return pq.poll()[0];
    }


    /**
     * https://www.youtube.com/watch?v=Lo23qFLhJNY 原始思路, 解題過程.
     */


    /**
     * https://www.youtube.com/watch?v=UP4RF_UjyNk
     * 2分
     */
    public int kthSmallest2(int[][] matrix, int k) {
        //将最大最小value 作为 low high
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        //离开while后, 都会
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            //j 是 矩阵的 宽
            int count = 0, j = matrix[0].length - 1;
            //i 是 矩阵的 高
            for (int i = 0; i < matrix.length; i++) {
                while (j >= 0 && matrix[i][j] > mid)
                    j--;
                //count 统计 小于等于 mid 的个数.
                //每次2分 都统计一次.
                count += (j + 1);
            }
            //最后一步lo, hi相邻, 相邻 2分后 肯定是落在 前者index.
            //因为k肯定存在,排除掉 <k后, 就是lo后一位, 如果是 >= k 就是2分后的index.
            if (count < k)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
        //都行
//        return hi;
    }
}
