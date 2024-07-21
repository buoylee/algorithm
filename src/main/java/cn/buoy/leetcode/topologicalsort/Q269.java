package cn.buoy.leetcode.topologicalsort;

import java.util.*;

public class Q269 {
    /**
     * 普通 topo 可解, 有時間看下 dfs
     * https://www.youtube.com/watch?v=5slT8ZfVKrQ dfs
     * 思路: 第一步, 写出字母間的先后关系(对比第一个不相同的char, 排前的优先级高). 然后就可以用 topo 或 dfs(由後往前排, 通過3色法, 表示"未訪問/正在訪問/已訪問") 来做了.
     * 有特例, 如果例子出现了 前缀相同, 但位数不够比的 两str, 这个无法作为比较关系; 假如 长的反而在前, 这个是无效arr, 无法排序.
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
        // 构建 字母前后(outdegree 和 indegree) 关系
        for (int i = 0; i < words.length - 1; i++) {
            // 對比 `curr` 和 `next` 元素.
            String word1 = words[i], word2 = words[i + 1];
            int letterIdx = 0;
            // 从第一个char开始比, 有其一长度越界, 停止对比.
            while (letterIdx < word1.length() && letterIdx < word2.length()) {
                char c1 = word1.charAt(letterIdx), c2 = word2.charAt(letterIdx);
                // 一旦出现不同char, 說明有明確的 "char 顺序" 出現.
                if (c1 != c2) {
                    outdegree.get(c1).add(c2);
                    indegree.put(c2, indegree.getOrDefault(c2, 0) + 1);
                    break;
                }
                letterIdx++;
            }
            // 細節: 如果后者 到达 结尾, 前者还没结束, 不合法arr.
            if (letterIdx == word2.length() && letterIdx < word1.length()) return "";
        }
        // 典型 topo 算法
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        // 入度为0 入队. 這裏的 indegree == 0, 實際上表示的是 "這個 字母序中" 可以作爲 "最前邊的 char".
        for (char c : indegree.keySet())
            if (indegree.get(c) == 0)
                queue.offer(c);
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            // 對 出隊(indegree == 0)的 node 的所有 "outdegree node" 的 indegree --
            for (char out : outdegree.get(c)) {
                indegree.put(out, indegree.get(out) - 1);
                // 再出現 入度为0 的 node, 加入queue中.
                if (indegree.get(out) == 0)
                    queue.offer(out);
            }
        }
        //判断结束后入度为0的个数, 如果和 原str.len 相等. 返回结果, 否则为空.
        return sb.length() == indegree.size() ? sb.toString() : "";
    }

    /**
     * 有空複習
     */
    public String alienOrder2(String[] words) {
        // 不同 node 的後續 char list
        Map<Character, List<Character>> outdegree = new HashMap<>();
        Set<Character> chars = new HashSet<>();
        for (String word : words)
            for (char c : word.toCharArray())
                chars.add(c);
        // Build the outdegree
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // 細節: 如果后者 到达 结尾, 前者还没结束, 不合法arr.
            if (word1.length() > word2.length() && word1.startsWith(word2)) return ""; // Invalid order
            // 構建 outdegree
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++)
                if (word1.charAt(j) != word2.charAt(j)) {
                    // 一對 word 只能找到 一個 letter 前後關係, 找到即退出.
                    outdegree.computeIfAbsent(word1.charAt(j), k -> new ArrayList<>()).add(word2.charAt(j));
                    break;
                }
        }
        // Perform DFS
        Map<Character, Boolean> visited = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char c : chars)
            // 如果 dfs() ture, 表示 該 char 已處理, 跳過, 繼續下一個 node 的 dfs. 只要不是正在訪問的 c(重複c, 有環出現), 都是可以的.
            if (!dfs(c, outdegree, visited, sb)) return "";
        return sb.reverse().toString();
    }

    // 表示 c 的訪問狀態
    // 視頻中有講, 3色法:
    // 0 未訪問
    // 1 正在訪問
    // 2 訪問結束
    // "由 最後 往 前" 排 char
    private boolean dfs(char c, Map<Character, List<Character>> outdegree, Map<Character, Boolean> visited, StringBuilder sb) {
        // 不存在 表示 未訪問
        if (visited.containsKey(c))
            return visited.get(c);
        // 正在訪問
        visited.put(c, false);
        for (char out : outdegree.getOrDefault(c, Collections.emptyList()))
            // 一旦遇到 正在訪問的 重複 char, 說明有環. 直接失敗.
            if (!dfs(out, outdegree, visited, sb))
                return false;
        // 訪問結束
        visited.put(c, true);
        sb.append(c);
        return true;
    }

    /**
     * dfs, 舊
     */
    private final int N = 26;

    public String alienOrder3(String[] words) {
        if (words == null)
            return "";
        // 防止 result 重複加入 相同 char
        boolean[][] visited = new boolean[N][N];
        int[] visit = new int[N];
        // 如果有環, 這些 words 不是成爲一個 合法的字母序.
        boolean valid = buildGraph(words, visited, visit);
        if (!valid)
            return "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if (visit[i] == 1)
                if (!dfs(visited, visit, sb, i))
                    return "";
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
            String s0 = words[i - 1];
            for (char c : s1.toCharArray()) {
                visit[c - 'a'] = 1;
            }

            if (i > 0) {
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
