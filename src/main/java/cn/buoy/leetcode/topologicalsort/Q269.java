package cn.buoy.leetcode.topologicalsort;

import java.util.*;

public class Q269 {
    /**
     * https://www.youtube.com/watch?v=5slT8ZfVKrQ
     * 画图! 第一步, 写出子母件的先后关系, 对比第一个不相同的char, 排前的优先级高. 然后就可以用topo 或 dfs 来做了.
     * 有特例, 如果例子出现了 前缀相同, 但位数不够比的 两str, 这个无法作为比较关系; 假如 长的反而在前, 这个是无效arr, 无法排序.
     * topo
     */
    public String alienOrder(String[] words) {
        Map<Character, Integer> indegree = new HashMap<>();
        Map<Character, List<Character>> outdegree = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0);
                outdegree.putIfAbsent(c, new ArrayList<>());
            }
        }

        //构建 字母 前后 关系
        for (int i = 0; i < words.length - 1; i++) {
            //取 `当前` 和 `下一个` 元素.
            String word1 = words[i], word2 = words[i + 1];
            int j = 0;
            //有其一长度越界, 停止对比.
            //从第一个char开始比.
            while (j < word1.length() && j < word2.length()) {
                char c1 = word1.charAt(j), c2 = word2.charAt(j);
                //一旦出现不同char, 添加 char 顺序 关系.
                if (c1 != c2) {
                    outdegree.get(c1).add(c2);
                    indegree.put(c2, indegree.getOrDefault(c2, 0) + 1);
                    break;
                }
                j++;
            }
            //如果后者 到达 结尾, 前者还没结束, 不合法arr.
            if (j == word2.length() && j < word1.length()) return "";
        }

        //接下来就是topo算法了
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        //入度为0先入队.
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            //对其出度的点 的入度--
            for (char next : outdegree.get(c)) {
                indegree.put(next, indegree.get(next) - 1);
                //如果有点入度为0, 加入queue中.
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        //判断结束后入度为0的个数, 如果和 原str.len 相等. 返回结果, 否则为空.
        return sb.length() == indegree.size() ? sb.toString() : "";
    }

    /**
     * dfs
     */
    private final int N = 26;

    public String alienOrder2(String[] words) {
        if (words == null) {
            return "";
        }

        boolean[][] visited = new boolean[N][N];
        int[] visit = new int[N];

        boolean valid = buildGraph(words, visited, visit);

        if (!valid) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if (visit[i] == 1) {
                if (!dfs(visited, visit, sb, i)) {
                    return "";
                }
            }
        }

        return sb.reverse().toString();
    }

    private boolean dfs(boolean[][] visited, int[] visit, StringBuilder sb, int i) {
        visit[i] = 2;

        for (int j = 0; j < N; j++) {
            if (visited[i][j]) {
                if (visit[j] == 2) {
                    return false;
                }

                if (visit[j] == 1) {
                    if (!dfs(visited, visit, sb, j)) {
                        return false;
                    }
                }
            }
        }

        sb.append((char) ('a' + i));
        visit[i] = 3;
        return true;
    }

    private boolean buildGraph(String[] words, boolean[][] visited, int[] visit) {

        for (int i = 0; i < words.length; i++) {
            String s1 = words[i];

            for (char c : s1.toCharArray()) {
                visit[c - 'a'] = 1;
            }

            if (i > 0) {
                String s0 = words[i - 1];
                int l0 = s0.length();
                int l1 = s1.length();

                for (int j = 0; j < l0; j++) {
                    if (j == l1) {
                        return false;
                    }

                    if (s0.charAt(j) != s1.charAt(j)) {
                        visited[s0.charAt(j) - 'a'][s1.charAt(j) - 'a'] = true;
                        break;
                    }
                }
            }
        }

        return true;
    }
}
