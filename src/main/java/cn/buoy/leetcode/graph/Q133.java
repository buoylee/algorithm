package cn.buoy.leetcode.graph;

import cn.buoy.leetcode.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Q133 {
    /**
     * 簡單
     * https://www.youtube.com/watch?v=dhmIGSv-XXo
     * 關鍵在在如何排除 重複copy的點. 下邊都是講clone過的node放入map, 存在則跳過該node.
     */
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    /**
     * 這個比較巧妙, 直接一個遞歸完成.
     */
    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) return null;
        if (map.containsKey(node.val))
            return map.get(node.val);
        // 没复制过, 複製 node, 马上放入 map
        UndirectedGraphNode clone = new UndirectedGraphNode(node.val);
        map.put(clone.val, clone);
        for (UndirectedGraphNode neighbor : node.neighbors)
            // 关键: 插入 neighbors時, 直接遞歸.
            clone.neighbors.add(clone(neighbor));
        return clone;
    }

    /**
     * 視頻代碼
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        HashMap<Node, Node> map = new HashMap<>();
        // 两步: 先copy点, 再copy边
        copyNode(node, map);
        copyNei(node, map, new HashSet<Node>());

        return map.get(node);
    }

    /**
     * clone node
     *
     * @param node
     * @param map
     */
    private void copyNode(Node node, HashMap<Node, Node> map) {
        if (map.containsKey(node)) return;
        map.put(node, new Node(node.val, new ArrayList<>()));

        for (Node nei : node.neighbors) {
            copyNode(nei, map);
        }
    }

    /**
     * 關聯 node
     *
     * @param node
     * @param map
     * @param visited
     */
    private void copyNei(Node node, HashMap<Node, Node> map, HashSet<Node> visited) {
        if (visited.contains(node)) return;
        visited.add(node);

        for (Node nei : node.neighbors) {
            map.get(node).neighbors.add(map.get(nei));
            copyNei(nei, map, visited);
        }
    }


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
