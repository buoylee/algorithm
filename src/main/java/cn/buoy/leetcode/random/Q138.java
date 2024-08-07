package cn.buoy.leetcode.random;

import cn.buoy.leetcode.RandomListNode;

import java.util.HashMap;
import java.util.Map;

public class Q138 {
    /**
     * 遞歸, 寫起來比較簡單.
     */
    private HashMap<RandomListNode, RandomListNode> visited = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;
        // 如果该节点已经被复制过，直接返回它的副本
        if (visited.containsKey(head))
            return visited.get(head);
        // 创建一个新节点
        RandomListNode node = new RandomListNode(head.val);
        // 将新节点加入到哈希表中
        visited.put(head, node);
        // 递归复制 next 和 random 节点
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }

    /**
     * https://www.youtube.com/watch?v=UWt3qmjx8qo
     * 先复制出所有点
     * 然后 设置指针时, 都指向 map 对应 的value(node).
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return null;

        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.val));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }
}

