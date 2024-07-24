package cn.buoy.leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Q146 {
    /**
     * 簡單, 視頻, 沒有難度, 看一下就好.
     * https://www.youtube.com/watch?v=PMkLaDmb2Vc
     * 思路: 用 雙向鏈表 來表示, 按最近是否操作過來排序, 一旦被 操作(add, get), 都會被移動到 head, 一旦超過容量, 則 彈出 tail.
     * 爲了提高搜索(get)速度, 用 map 來 存儲 key: 鏈表 node.
     */

    class LRUCache {
        private class Node {
            int key, value;
            Node prev, next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int capacity, size;
        private HashMap<Integer, Node> cache;
        private Node dummyHead, dummyTail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.cache = new HashMap<>();
            dummyHead = new Node(-1, -1);
            dummyTail = new Node(-1, -1);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null)
                return -1;
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            // 如果是新加入的 value, 放入 map 和 "加到 list head" 和 size++
            if (node == null) {
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addToHead(newNode);
                size++;
                // 超過容量就 remove tail
                if (size > capacity) {
                    Node tail = removeTail();
                    cache.remove(tail.key);
                    size--;
                }
            } else { // 存在 value, 只需要移動到 list head
                node.value = value;
                moveToHead(node);
            }
        }

        // 插到 dummyHead 和 dummyHead.next 之間
        private void addToHead(Node node) {
            node.prev = dummyHead;
            node.next = dummyHead.next;
            dummyHead.next.prev = node;
            dummyHead.next = node;
        }

        // node.prev 和 node.next 雙向關聯
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        private Node removeTail() {
            Node res = dummyTail.prev;
            removeNode(res);
            return res;
        }
    }

    class LRUCache3 {
        int capacity;
        Node dummyHead;
        Node dummyTail;
        Map<Integer, Node> map;

        public LRUCache3(int capacity) {
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
