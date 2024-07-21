package cn.buoy.leetcode.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q210 {
    /**
     * https://www.youtube.com/watch?v=qunhYX91VLU
     * 几乎和 207 没区别, 在 pop 出 queue 時, 同时放进res就好.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int index = 0;
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());
        for (int[] prereq : prerequisites) {
            // 把 "相同前置 course" 的 course, 歸類到各自 list.
            graph.get(prereq[1]).add(prereq[0]);
            // 统计 每个 node 的 "入度數量"
            indegree[prereq[0]]++;
        }
        // 用於處理 入度 == 0 的 node, 每個 進入 queue 的 node. 說明是可以完成的"沒有前置 course" 的 course.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++)
            if (indegree[i] == 0)
                queue.add(i);
        // 有一個 入度爲0的 node, 代表 可以完成一個 course.
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            // 入度 == 0 的点, 就是 路過的 course.
            res[index++] = course;
            // 把 "當前入度爲0的 node" 作爲 "前置 course" 的 course 的入度--
            for (int neighbor : graph.get(course)) {
                indegree[neighbor]--;
                // 一旦 course的 入度 == 0, 又會被 offer 進 queue
                if (indegree[neighbor] == 0)
                    queue.add(neighbor);
            }
        }
        return numCourses == count ? res : new int[0];
    }

    /**
     * 上边解法时空比较差, 有空优化
     */
    // FIXME: 2021/5/5
    int idx;
    int[] ans;

    public int[] findOrder2(int numCourses, int[][] prerequisites) {

        ArrayList[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] pre : prerequisites) {
            adj[pre[0]].add(pre[1]);
        }
        //0 表示没访问过
        //1 访问过 但没成功
        //2 访问过 成功
        //dfs 每次都是访问完一条, 再下一条; 不要想岔了, 不会遇到另一条 还在走的`1`.
        //dp[i] 也是个dp.
        int[] visited = new int[numCourses];
        ans = new int[numCourses];
        idx = 0;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && !dfs(adj, i, visited)) {
                return new int[0];
            }
        }
        return ans;
    }

    //从某点 往回走, 如果 到头(没有前置依赖) 且 没有重复访问(环), 则true.
    boolean dfs(ArrayList<Integer>[] adj, int v, int[] visited) {
        if (visited[v] == 1) return false;
        if (visited[v] == 2) return true;

        visited[v] = 1;

        //往源找.
        for (int x : adj[v]) {
            if (!dfs(adj, x, visited)) return false;
        }
        ans[idx++] = v;
        visited[v] = 2;
        return true;
    }
}
