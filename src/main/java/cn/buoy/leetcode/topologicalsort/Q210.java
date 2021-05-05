package cn.buoy.leetcode.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q210 {
    /**
     * https://www.youtube.com/watch?v=qunhYX91VLU
     * 几乎没区别, 把放在queue里的元素, 同时放进res就好.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int k = 0;
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        //每个点的入度统计
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        //选出没有入度的点 进入queue
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                //差别
                res[k++] = i;
            }
        }
        while (!queue.isEmpty()) {
            //已完成一个课程排序
            numCourses--;
            //弹出 入度为0 的点, 遍历所有prerequisites(边), 对依赖该点 的点 的入度--
            int course = queue.poll();
            //
            for (int[] pair : prerequisites) {
                //如果有依赖该点
                if (pair[1] == course) {
                    //--
                    indegree[pair[0]]--;
                    //如果某点 所依赖的点(入度)为0, 可以放入0依赖queue中, 等待`排序`和`接下来的其他点对`该某点有依赖的入度的入--`
                    if (indegree[pair[0]] == 0) {
                        //放入0依赖queue中
                        queue.add(pair[0]);
                        //差别
                        res[k++] = pair[0];
                    }
                }
            }
        }
        return numCourses == 0 ? res : new int[0];
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
