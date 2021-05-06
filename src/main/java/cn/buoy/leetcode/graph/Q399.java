package cn.buoy.leetcode.graph;

import java.util.*;

public class Q399 {
    /**
     * https://www.youtube.com/watch?v=u9LE_7apB38
     * 关键: 当 有向带权图 处理
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //<a,<b,value>> 表示 a到b的权值为value
        Map<String, Map<String, Double>> m = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            m.putIfAbsent(equations.get(i).get(0), new HashMap<>());
            m.putIfAbsent(equations.get(i).get(1), new HashMap<>());
            //对应 a->b
            m.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);
            //对应 b->a
            m.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++)
            //seen每次都 new 不需要backtracking
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, m, new HashSet<>());
        return res;
    }

    double dfs(String start, String tail, double tmp, Map<String, Map<String, Double>> map, Set<String> seen) {
        //如果没有起点node, 或已经visited 返回失败-1
        if (!map.containsKey(start) || !seen.add(start)) return -1;
        //到了结尾
        if (start.equals(tail)) return tmp;
        Map<String, Double> next = map.get(start);
        for (String c : next.keySet()) {
            double result = dfs(c, tail, tmp * next.get(c), map, seen);
            if (result != -1) return result;
        }
        return -1;
    }
}
