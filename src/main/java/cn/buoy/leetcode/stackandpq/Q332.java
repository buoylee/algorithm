package cn.buoy.leetcode.stackandpq;

import java.util.*;

public class Q332 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=LKSdX31pXjY
     * 思路: 一直往下找可用的機票, 優先找字母小的(A~Z), 直到 票都用完.
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets)
            graph.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(ticket.get(1));
        // 按字母順序
        graph.values().forEach(Collections::sort);
        LinkedList<String> result = new LinkedList<>();
        // 票數 應該 比 node數 少1.
        dfs("JFK", graph, result, tickets.size() + 1);
        return result;
    }

    private boolean dfs(String start, Map<String, List<String>> graph, LinkedList<String> result, int nodeCount) {
        result.add(start);
        // 關鍵: 用完 ticket 數量.
        if (result.size() == nodeCount) return true;
        if (graph.containsKey(start)) {
            List<String> nextNodes = graph.get(start);
            // 從 A - Z 遍歷
            for (int i = 0; i < nextNodes.size(); i++) {
                String nextNode = nextNodes.remove(i);
                if (dfs(nextNode, graph, result, nodeCount)) return true;
                // 回溯 可用票到原位..
                nextNodes.add(i, nextNode);
            }
        }
        // 回溯 結果 node.
        result.removeLast();
        return false;
    }

    /**
     * dfs
     */
    Map<String, PriorityQueue<String>> dists = new HashMap<>();
    List<String> result = new LinkedList();

    public List<String> findItinerary2(List<List<String>> tickets) {
        for (List<String> ticket : tickets)
            dists.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        visit("JFK");
        return result;
    }

    void visit(String node) {
        while (dists.containsKey(node) && !dists.get(node).isEmpty())
            visit(dists.get(node).poll());
        result.add(0, node);
    }

    public static void main(String[] args) {
        Q332 solution = new Q332();
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );
        System.out.println(solution.findItinerary(tickets));
        ArrayList<String> strs = new ArrayList<>();
//        strs.add("JFB");
//        strs.add("JFA");
//        strs.add("JFC");
//        Collections.sort(strs);
//        System.out.println(strs);
//        String remove = strs.remove(0);
//        System.out.println(remove);

    }
}
