package cn.buoy.leetcode.dfsandbfs;

import java.util.*;

public class Q126 {
    /**
     * https://www.youtube.com/watch?v=lmypbtgdpuQ
     * 理解优化是关键, 代码算简单.
     * 优化方向: 1. 找最短距离; 2. 找step 对应的.
     */
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        //使用过的str, set提高检索速度
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();
        //所有和某个str只差一个char 的str
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
        //每个从 start 到 str 的距离.
        HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
        //每一个子序列
        ArrayList<String> solution = new ArrayList<String>();

        //把自己也放到 dict里.
        //建nodeNeighbors
        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);
        dfs(start, end, dict, nodeNeighbors, distance, solution, res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<String>());

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        //start distance 置0
        distance.put(start, 0);

        //普通bfs
        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                //找到所有邻居
                ArrayList<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    //都塞进 对应的 nodeNeighbors里
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {// Check if visited
                        //然后跟新距离.
                        distance.put(neighbor, curDistance + 1);
                        //标记结束str
                        //这里试了,可以直接break;
                        if (end.equals(neighbor))// Found the shortest path
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }

            if (foundEnd)
                break;
        }
    }

    // Find all next level nodes.
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();

        //用`不同字母`替换`每一个char`,找出所有 one step 的str.
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
    private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        //如果 str 到达 target
        if (end.equals(cur)) {
            //copy后放入res
            res.add(new ArrayList<String>(solution));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                //如果下一个str距离 和 当前str 距离 差1, 满足, 继续往下走
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
        //backtracking
        solution.remove(solution.size() - 1);
    }
}
