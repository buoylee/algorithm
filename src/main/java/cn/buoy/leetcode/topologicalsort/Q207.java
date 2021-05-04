package cn.buoy.leetcode.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q207 {
    /**
     * https://www.youtube.com/watch?v=oa6uR2yNG_s
     * 最基本的拓扑解法
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
                    }
                }
            }
        }
        return numCourses == 0;
    }


    /**
     * 判断每个点 是否有环, 无则true.
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList();
        }

        boolean[] visited = new boolean[numCourses];
        //以dp[i] 以i开头的点是否有环
        boolean[] dp = new boolean[numCourses];
        //对应 X点 能到 其他点的集合, 例:0:{1, 2, 3}
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        // check if there is a cycle for each class
        //是否有环
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i, dp)) {
                return false;
            }
        }
        return true;
    }


    private boolean dfs(ArrayList<Integer>[] graph, boolean[] visited, int course, boolean[] mem) {
        //有环
        if (visited[course]) return false;
        if (mem[course]) return mem[course];
        //设置访问过
        visited[course] = true;
        for (int i = 0; i < graph[course].size(); i++) {
            if (!dfs(graph, visited, (int) graph[course].get(i), mem)) {
                return false;
            }
        }
        //backtracking
        visited[course] = false;
        mem[course] = true;
        return true;
    }
}
