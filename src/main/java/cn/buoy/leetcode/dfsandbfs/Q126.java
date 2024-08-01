package cn.buoy.leetcode.dfsandbfs;

import java.util.*;

public class Q126 {
    /**
     * 算简单, 視頻, 註釋, 比較快.
     * https://www.youtube.com/watch?v=PblfQrdWXQ4
     * 思路: 先用 bfs 一层层(步) 构建 neighbor 图,
     * 为了不走回头路, 且 bfs 就是最短的可能, 需要记录每个走到的点顺便记录原点到他的距离 distanceMap. 当渐渐没有新遇见(distanceMap 没有记录过的)的 node 加入到 queue, queue无 ele 可取时, 结束 bfs.
     * 最后, 得到 最短距离树后, 用 backtracking, 从 start 开始 到 end, 能到达 end, 就 temp 加入 result 中.
     */
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        // set提高检索速度
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();
        // map<value, neighbor list>, 所有和某个str只差一个 char 的 str list.
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<>();// Neighbors for every node
        // map<node, "从 start 到 node 的距离">
        HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
        // 用於構建子數組的 tempList
        ArrayList<String> tempList = new ArrayList<String>();
        // 用於 bfs 的起始元素.
        dict.add(start);
        // 構建 從 start 到 end 的單向樹 nodeNeighbors, 和 start 到 node 的距離 distance.
        bfs(start, end, dict, nodeNeighbors, distance);
        // 構建 result 子數組.
        dfs(start, end, dict, nodeNeighbors, distance, tempList, res);
        return res;
    }

    /**
     * DFS: output all paths with the shortest distance.
     * 用 bfs 構建了
     * nodeNeighbors map<node str, node neighbor list(neighbor就是和node差一個char的node)>
     * 和 distance map<node str, start 到 node 的距離>.
     * 這個 nodeNeighbors 只能是單向start->end的變化, 不會重複出現node, 注意看 "!distance.containsKey(neighbor)", 用已经 "计算过距离的 node" 排除曾經出現的 node.
     */
    private void bfs(String start, String end, Set<String> dict,
                     HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<>()); // 给 str 放 "空 list" 而已
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        // init
        distance.put(start, 0);
        // 典型 bfs
        while (!queue.isEmpty()) {
            // count 表示 上一層所有元素個數.
            int count = queue.size();
            boolean foundEnd = false;
            // 遍歷上一層所有元素, 逐一找出他們的 neighbors, 全部再推入到 queue.
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                // 单纯找到 word 的所有 neighbor("和 node 只差一個 char" 的 word).
                // 關鍵: 同一个 neighbor 是可以出现在 "不同的 node" 下的. 所以在構建結果時, 不止需要判斷 neighbor, 還要判斷 distance.
                ArrayList<String> neighbors = getNeighbors(cur, dict);
                // '所有 cur 的 neighbors 的 distance' = cur distance++, 然後把 neighbor str 推入到 queue,
                for (String neighbor : neighbors) {
                    // 塞进 "对应 str" 的 nodeNeighbors 里
                    nodeNeighbors.get(cur).add(neighbor);
                    // 關鍵: 已经计算过 distance 的 node, 不能重复计算, 因为 bfs 已经是最短的了, 也排除了走回头路的可能. 只有當第一次發現某個 neighbor 才會推入 queue.
                    if (!distance.containsKey(neighbor)) { // Check if visited
                        // 和插入相對的距离.
                        distance.put(neighbor, curDistance + 1);
                        // 找到 end
                        if (end.equals(neighbor)) // Found the shortest path
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }
            // 因爲只需要返回最短的(但可能同一層有多個解, 所以要同一層結束時, 才檢查 foundEnd), 因爲是最短的, 按照 bfs 原理, 所以一旦發現某層可以到達 end, 那就是最短的.
            if (foundEnd)
                break;
        }
    }

    /**
     * Find all next level nodes.
     * 单纯找到所有和 node 只差一個字母的 單詞, 返回 ArrayList<String>.
     */
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char[] chs = node.toCharArray();
        //用`a~z`替换 node 中`每一个 char`,找出所有 one step 的str.
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                // 用于修改后比较完, 然后还原
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs)))
                    res.add(String.valueOf(chs));
                // 还原
                chs[i] = old_ch;
            }
        }
        return res;
    }

    /**
     * DFS: output all paths with the shortest distance.
     * 由 最短路径树 dfs 構建結果子數組.
     */
    private void dfs(String cur, String end, Set<String> dict,
                     HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance,
                     ArrayList<String> tempList, List<List<String>> res) {
        tempList.add(cur);
        // 如果 str 到达 target
        if (end.equals(cur)) {
            // copy 放入 res
            res.add(new ArrayList<>(tempList));
        } else {
            // 關鍵: 對於不同的 node, 他的 neighbor 是可以重複的.
            for (String next : nodeNeighbors.get(cur))
                // 如果 "下一个 str 距离" 和 "当前 str 距离" 差1, 满足, 继续往下走
                if (distance.get(next) == distance.get(cur) + 1)
                    dfs(next, end, dict, nodeNeighbors, distance, tempList, res);
        }
        // backtracking
        tempList.remove(tempList.size() - 1);
    }
}
