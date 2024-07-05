package cn.buoy.leetcode.graph;

import java.util.*;

public class Q399 {
    /**
     * https://www.youtube.com/watch?v=u9LE_7apB38
     * https://www.youtube.com/watch?v=berj4Xm_YTY 看這個短的
     * 簡單, 看視頻.
     * 思路: 当 有向带权图 处理, a / b = 2, b / c = 1 => a / c = 2;
     * a -> b -> c;
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //<a,<b,value>> 表示 a到b的权值为value
        Map<String, Map<String, Double>> equationMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            // 例如: 一個 a/b = 2, 可以轉爲 b/a = 0.5
            equationMap.putIfAbsent(equations.get(i).get(0), new HashMap<>());
            equationMap.putIfAbsent(equations.get(i).get(1), new HashMap<>());
            //对应 a->b
            equationMap.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);
            //对应 b->a
            equationMap.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++)
            //seen每次都 new 不需要backtracking
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, equationMap, new HashSet<>());
        return res;
    }

    /**
     * @param start
     * @param tail
     * @param tmpValue    中途的值
     * @param equationMap Map<String, Map<String, Double>>
     * @param used        已被使用的 被除數(node)
     * @return
     */
    double dfs(String start, String tail, double tmpValue, Map<String, Map<String, Double>> equationMap, Set<String> used) {
        //如果没有起点node, 或已经 used 返回失败-1
        if (!equationMap.containsKey(start) || !used.add(start))
            return -1;
        //到了结尾
        if (start.equals(tail))
            return tmpValue;
        Map<String, Double> nextMap = equationMap.get(start);
        for (String nextNode : nextMap.keySet()) {
            double result = dfs(nextNode, tail, tmpValue * nextMap.get(nextNode), equationMap, used);
            if (result != -1)
                return result;
        }
        return -1;
    }
}
