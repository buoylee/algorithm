package cn.buoy.leetcode.dfsandbfs;

import java.util.*;

public class Q126 {
    /**
     * https://www.youtube.com/watch?v=PblfQrdWXQ4
     * 稍微看下視頻, 然後看註釋, 比較快.
     */
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        //set提高检索速度
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();
        // key : value = node : neighbor list, 所有和某个str只差一个 char 的 str list.
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
        //每个 node的 '从 start 到 node 的距离'.
        HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
        //用於構建子數組的 tempList
        ArrayList<String> tempList = new ArrayList<String>();

        //用於 bfs 的起始元素.
        dict.add(start);
        // 構建 從 start 到 end 的單向樹 nodeNeighbors, 和 start 到 node 的距離 distance.
        bfs(start, end, dict, nodeNeighbors, distance);
        // dfs 構建結果子數組.
        dfs(start, end, dict, nodeNeighbors, distance, tempList, res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).

    /**
     * 用 bfs 構建了 nodeNeighbors(node str : node neighbor list(neighbor就是和node差一個char的node)) 和 distance(node str : start 到 node 的距離).
     * 這個 nodeNeighbors 只能是單向start->end的變化, 不會重複出現node, 注意看 "!distance.containsKey(neighbor)", 巧妙的排除了曾經出現的 node.
     */
    private void bfs(String start, String end, Set<String> dict,
                     HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<String>());

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        //start distance 置0
        distance.put(start, 0);

        //普通bfs
        while (!queue.isEmpty()) {
            // count 表示 上一層所有元素個數.
            int count = queue.size();
            boolean foundEnd = false;
            // 遍歷上一層所有元素, 逐一找出他們的 neighbors, 全部再推入到 queue.
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                // 關鍵: 找到所有和 node 只差一個字母的 單詞. 注意, 這裏的 neighbor 在不同的 node 下是可以重複的. 所以在構建結果時, 不止需要判斷 neighbor, 還要判斷 distance.
                ArrayList<String> neighbors = getNeighbors(cur, dict);
                // 把上一層其中一個元素的'所有 neighbors 的 distance' = 這個元素的 distance++, 然後把 neighbor str 推入到 queue,
                for (String neighbor : neighbors) {
                    //塞进 对应的 nodeNeighbors里
                    nodeNeighbors.get(cur).add(neighbor);
                    // 關鍵: 通過判斷 !distance.containsKey(neighbor), 排除掉了 曾經出現過的node節點, 當第一次發現某個 neighbor 才會推入 queue.
                    if (!distance.containsKey(neighbor)) {// Check if visited
                        // 和插入相對的距离.
                        distance.put(neighbor, curDistance + 1);
                        // 找到 end
                        // ???这里试了,可以直接break;
                        if (end.equals(neighbor))// Found the shortest path
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }

            // 因爲只需要返回最短的(可能同一層有多個, 所以要同一層結束時, 才檢查 foundEnd), 因爲是最短的, 按照bfs原理, 所以一旦發現某層可以到達end, 那就是最短的.
            if (foundEnd)
                break;
        }
    }

    /**
     * Find all next level nodes.
     * 找到所有和 node 只差一個字母的 單詞, 返回 ArrayList<String>.
     */
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char[] chs = node.toCharArray();

        //用`a~z`替换 node 中`每一个char`,找出所有 one step 的str.
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                //还原
                chs[i] = old_ch;
            }

        }
        return res;
    }

    // DFS: output all paths with the shortest distance.

    /**
     * dfs 構建結果子數組.
     */
    private void dfs(String cur, String end, Set<String> dict,
                     HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance,
                     ArrayList<String> tempList, List<List<String>> res) {
        tempList.add(cur);
        //如果 str 到达 target
        if (end.equals(cur)) {
            //copy后放入res
            res.add(new ArrayList<String>(tempList));
        } else {
            // 關鍵: 對於不同的 node, 他的 neighbor 是可以重複的.
            for (String next : nodeNeighbors.get(cur)) {
                //如果下一个str距离 和 当前str 距离 差1, 满足, 继续往下走
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, dict, nodeNeighbors, distance, tempList, res);
                }
            }
        }
        //backtracking
        tempList.remove(tempList.size() - 1);
    }
}
