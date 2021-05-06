package cn.buoy.leetcode.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q261 {
    public static void main(String[] args) {
        int[][] ints = new int[2][2];
        ints[0] = new int[]{1, 0};
        ints[1] = new int[]{2, 1};
        Q261 q261 = new Q261();
        boolean b = q261.validTree(3, ints);

    }

    /**
     * https://www.youtube.com/watch?v=vsIb9B84Rt8
     * 可以用dfs检查是否有环.
     * 也可以用 `父node并查集实现` 来 `检查是否有环`.
     * 这里并查集解法是 将node指向相连的node.
     */
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        //初始化为-1, 表示这个index 指向自己, 返回自己的index.
        Arrays.fill(nums, -1);

        // perform union find
        //只有边 不会重复(相反)的情况, 才可以这么做, 边比 点少1, 就不会造成 这种方法相连.
        for (int i = 0; i < edges.length; i++) {
            //不会出现死循环,
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);

            // if two vertices happen to be in the same set
            // then there's a cycle
            //递归往父node 找, 最后父node 如果相同, 返回false.
            if (x == y) return false;

            // union
            //前者 指向 后者
            nums[x] = y;
        }

        //如果都没环, 且 点 比 边 少1, 则true.
        return edges.length == n - 1;
    }

    int find(int[] nums, int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }


    /**
     * dfs判断是否有环
     *
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree2(int n, int[][] edges) {
        // initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        // initialize vertices
        for (int i = 0; i < n; i++)
            adjList.add(i, new ArrayList<Integer>());

        // add edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        // make sure there's no cycle
        if (hasCycle(adjList, 0, visited, -1))
            return false;

        // make sure all vertices are connected
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    // check if an undirected graph has cycle started from vertex u
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);

            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u)))
                return true;
        }

        return false;
    }
}
