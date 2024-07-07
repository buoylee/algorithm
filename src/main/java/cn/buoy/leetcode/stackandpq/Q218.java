package cn.buoy.leetcode.stackandpq;

import java.util.*;

public class Q218 {
    /**
     * 好理解, 視頻, 註釋.
     * https://www.youtube.com/watch?v=v5CMa5MUGCo
     * 思路: 轉爲處理 List<位置 : 高度變化(+/-)> 的影射關係, 用pq來記錄 highest, 當 位置改變, highest 也改變時, 記錄 一點.
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 轉爲記錄 List<位置 : 高度變化> 影射關係
        // 例: (start, end, height) => [start, -height], [end, height],
        // 相同位置情況下, 小的排在前, 因爲高度上升下降用+/-符號區分, (實際上 'highter 的高度' 先進後出pq).
        List<int[]> changingHeights = new ArrayList<>();
        for (int[] b : buildings) {
            changingHeights.add(new int[]{b[0], -b[2]});
            changingHeights.add(new int[]{b[1], b[2]});
        }
        Collections.sort(changingHeights, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.offer(0);
        int prevHighest = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (int[] changingHeight : changingHeights) {
            if (changingHeight[1] < 0) {
                // 因爲 height 升高記錄爲負數.
                pq.offer(-changingHeight[1]);
            } else {
                pq.remove(changingHeight[1]);
            }
            // 關鍵: 不要搞混了, 這個 Highest 是 當到達他的結束位才會pop出, 不是比之前的點.
            int currHighest = pq.peek();
            // 高度發生改變就會記錄
            // 關鍵: 如果高的不是先進後出, 會導致這裏 相同位置 不同高度記錄2次.
            if (prevHighest != currHighest) {
                result.add(Arrays.asList(changingHeight[0], currHighest));
                prevHighest = currHighest;
            }
        }
        return result;
    }

    /**
     * https://www.youtube.com/watch?v=tQiXaCT0ndE
     */
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        //各个building 开始/结束 相关点的 map list.
        Map<Integer, List<int[]>> cps = new TreeMap<>(); // ordered by the critical points
        for (int[] b : buildings) {
            //给building 的左右边界 的点, 创建list存放相同的点的 build.
            cps.putIfAbsent(b[0], new LinkedList<>());
            cps.putIfAbsent(b[1], new LinkedList<>());
            cps.get(b[0]).add(b);
            cps.get(b[1]).add(b);
        }

        // heap for the currently active buildings
        //用来拿max, 关键在于 维护building 进入 与 离开.
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] b1, int[] b2) {
                return Integer.compare(b2[2], b1[2]);
            }
        });

//        List<int[]> res = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        // iterate from left to right over the critical points
        for (Map.Entry<Integer, List<int[]>> entry : cps.entrySet()) {
            int c = entry.getKey();
            List<int[]> bs = entry.getValue();

            for (int[] b : bs) {
                if (c == b[0]) { // this critical point is a left edge of building `b`
                    heap.add(b);
                } else { // right edge
                    heap.remove(b);
                }
            }

            if (heap.isEmpty()) {
                // the heap is empty, so the skyline is 0
//                res.add(new int[]{c, 0});
                res.add(Arrays.asList(c, 0));
            } else {
                int h = heap.peek()[2];
                if (res.isEmpty() || res.get(res.size() - 1).get(1) != h) {
                    // only add the highest rectangle if it different than before
//                    res.add(new int[]{c, h});
                    res.add(Arrays.asList(c, h));
                }
            }
        }

        return res;
    }

    /**
     * fast
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline3(int[][] buildings) {
        return merge(buildings, 0, buildings.length - 1);
    }

    private LinkedList<List<Integer>> merge(int[][] buildings, int lo, int hi) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (lo > hi) {
            return res;
        } else if (lo == hi) {
            res.add(Arrays.asList(buildings[lo][0], buildings[lo][2]));
            res.add(Arrays.asList(buildings[lo][1], 0));
            return res;
        }
        int mid = lo + (hi - lo) / 2;
        LinkedList<List<Integer>> left = merge(buildings, lo, mid);
        LinkedList<List<Integer>> right = merge(buildings, mid + 1, hi);
        int leftH = 0, rightH = 0;
        while (!left.isEmpty() || !right.isEmpty()) {
            long x1 = left.isEmpty() ? Long.MAX_VALUE : left.peekFirst().get(0);
            long x2 = right.isEmpty() ? Long.MAX_VALUE : right.peekFirst().get(0);
            int x = 0;
            if (x1 < x2) {
                List<Integer> temp = left.pollFirst();
                x = temp.get(0);
                leftH = temp.get(1);
            } else if (x1 > x2) {
                List<Integer> temp = right.pollFirst();
                x = temp.get(0);
                rightH = temp.get(1);
            } else {
                x = left.peekFirst().get(0);
                leftH = left.pollFirst().get(1);
                rightH = right.pollFirst().get(1);
            }
            int h = Math.max(leftH, rightH);
            if (res.isEmpty() || h != res.peekLast().get(1)) {
                res.add(Arrays.asList(x, h));

            }
        }
        return res;
    }
}
