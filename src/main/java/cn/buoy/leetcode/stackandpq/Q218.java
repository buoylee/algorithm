package cn.buoy.leetcode.stackandpq;

import java.util.*;

public class Q218 {
    /**
     * https://www.youtube.com/watch?v=tQiXaCT0ndE
     * <p>
     * https://www.youtube.com/watch?v=v5CMa5MUGCo
     */
//    public List<int[]> getSkyline(int[][] buildings) {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        //各个building 开始结束 相关点的 map list.
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


    //    public List<int[]> getSkyline2(int[][] buildings) {
    public List<List<Integer>> getSkyline2(int[][] buildings) {

//        List<int[]> result = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if (prev != cur) {
//                result.add(new int[]{h[0], cur});
                result.add(Arrays.asList(h[0], cur));
                prev = cur;
            }
        }
        return result;
    }
}
