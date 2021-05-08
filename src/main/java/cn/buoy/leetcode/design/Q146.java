package cn.buoy.leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Q146 {
    /**
     * https://www.youtube.com/watch?v=PMkLaDmb2Vc
     */
    class LRUCache {
        int capacity;
        Node dummyHead;
        Node dummyTail;
        Map<Integer, Node> map;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            dummyHead = new Node(0, 0);
            dummyTail = new Node(0, 0);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        //兼顾了`调整优先级`的功能
        public int get(int key) {
            //put()时, 已预先放入map, 这行不会生效
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            //如果不是put()新元素, 需要update的 prev 和 next
            if (node.prev != null && node.next != null) {
                //a -> b -> c => a -> c
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            //插到 第一个有效元素位(dummyHead后)
            node.next = dummyHead.next;
            dummyHead.next.prev = node;
            dummyHead.next = node;
            node.prev = dummyHead;
            return node.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                //已存在
                map.get(key).val = value;
                get(key);
            } else {
                //不存在
                Node node = new Node(key, value);
                if (map.size() >= capacity) {
                    //当超过cap, 移除 队尾 有效元素
                    Node removed = map.get(dummyTail.prev.key);
                    map.remove(removed.key);
                    //倒数第2元素 指向 dummyTail, dummyTail prev 指向 removed.prev(倒数第2有效元素)
                    removed.prev.next = dummyTail;
                    dummyTail.prev = removed.prev;
                }
                //新node 放入map
                map.put(key, node);
                get(key);
            }
        }
    }

    class Node {
        Node prev;
        Node next;
        int val;
        int key;

        public Node(int key, int val) {
            prev = null;
            next = null;
            this.key = key;
            this.val = val;
        }
    }


    /**
     *
     */
    class LRUCache2 extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache2(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
}
