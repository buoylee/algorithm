package cn.buoy.leetcode.graph;

import java.util.*;

public class Q310 {
    /**
     * https://www.youtube.com/watch?v=pUtxTz134qM
     * 簡單. 先看視頻, 思路清晰, 然後看註釋就好.
     * 思路: 將 int[][] edges 轉化爲 node 的 度(一個node有幾個連接), 一個array, 還需要一個 map[node, neighbors](同一個關係, 需要各自保存對方的node.)
     */
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        // node的度
        int[] degree = new int[n];
        //保存着 各点的临边(每2個node的關係, 2個node需要各自保存另一個node, 用於當度爲1時彈出後, 他們的 neighbors 的度要 --)
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(i, new HashSet<>());
        for (int[] e : edges) {
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }
        //当degree 为 1时, 进入queue, 最后降为0时, 如果queue中再没有元素, 最后一轮的 degree 即为最低高度root.
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) q.offer(i);
        }
        while (!q.isEmpty()) {
            //上轮 degree == 1的node, 如果结束后 queue中 无元素, 此list就是答案.
            List<Integer> list = new ArrayList<>();
            //保存上轮个数, 只弹出上轮的.
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                list.add(cur);
                //关键代码: 单单看有点巧妙, 画图!
                //拿出 degree == 1的node 的临界点, --临界点的degree 即可.
                for (int neighbor : map.get(cur)) {
                    degree[neighbor]--;
                    // 每次 neighbor 度--, 馬上檢查當前 neighbor degree 是否爲1, 是就直接加入 queue
                    if (degree[neighbor] == 1)
                        q.offer(neighbor);
                }
            }
            // 直接把 list 放到 res 中, 只要 q.isEmpty(), 就是答案.
            res = list;
        }
        return res;
    }
}
