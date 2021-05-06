package cn.buoy.leetcode.graph;

import cn.buoy.leetcode.UndirectedGraphNode;

import java.util.HashMap;

public class Q133 {
    /**
     * https://www.youtube.com/watch?v=dhmIGSv-XXo
     * 视频方法 先拿每个点都复制出来, 但是neighbor 保留空, 填充neighbor 有递归, 需要将遍历过neighbor的node 加入到set中, 排除访问过的node.
     */
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) return null;

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.val);
        map.put(clone.val, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }
}
