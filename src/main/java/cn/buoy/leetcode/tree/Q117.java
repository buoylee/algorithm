package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.Node;

public class Q117 {
    /**
     * 簡單, 同116. 視頻
     * https://www.youtube.com/watch?v=W2xmj9ZYaHE
     * 思路: 還是 通過 檢查 parent 的 children 和 next 是否存在, 來連接 children 的 next.
     */
    public Node connect(Node root) {
        if (root == null) return root;
        // 下一行 的 head
        Node head = null;
        // 下一行 的 已連接的最後一個元素
        Node prev = null;
        // 上一行 的 curr
        Node curr = root;
        // 還有沒有 下一行的 head
        while (curr != null) {
            // 同一行 還有沒有 next
            while (curr != null) {
                // 有 leftChild 的處理
                if (curr.leftChild != null) {
                    // 找 下一行的 head
                    if (head == null) {
                        head = curr.leftChild;
                        prev = curr.leftChild;
                    } else {
                        prev.next = curr.leftChild;
                        prev = prev.next;
                    }
                }
                // 有 rightChild 的處理
                if (curr.rightChild != null) {
                    // 找 下一行的 head
                    if (head == null) {
                        head = curr.rightChild;
                        prev = curr.rightChild;
                    } else {
                        prev.next = curr.rightChild;
                        prev = prev.next;
                    }
                }
                // 同一行 next
                curr = curr.next;
            }
            // 下一行的 head 成爲 curr
            curr = head;
            // 爲 下下行 重置指針.
            prev = null;
            head = null;
        }
        return root;
    }

    /**
     * 只是實現層面, 上邊稍微直觀一點.
     */
    public Node connect2(Node root) {
        Node res = root;
        Node head = root;//The left most node in the lower level
        Node prev = null;//The previous node in the lower level
        Node curr = null;//The current node in the upper level
        while (head != null) {
            //每一层开始, 用 cur 替代 上次上层求出的本层的head, 并将head = null, 等待第一个出现的 后代node. 作为下一层的head.
            curr = head;
            // 存在的 上一個 元素
            prev = null;
            // 當前層 的 head
            head = null;
            // 直到 同层 來到了 末尾
            while (curr != null) {
                if (curr.leftChild != null) {
                    //如果有prev, 连上即可.
                    if (prev != null)
                        prev.next = curr.leftChild;
                    else
                        //如果prev == null, 说明这层循环刚开始, left有值, 则设置为head.
                        //关键在这, 这里就将 下层的head 找到了. 遍历上层时, 第一个出现的就是 head. 同理right node.
                        head = curr.leftChild;
                    prev = curr.leftChild;
                }
                //同理上边
                if (curr.rightChild != null) {
                    if (prev != null)
                        prev.next = curr.rightChild;
                    else
                        head = curr.rightChild;
                    prev = curr.rightChild;
                }
                //遍历同层下一个.
                curr = curr.next;
            }
        }
        return res;
    }

    /**
     * 写法不同而已
     */
    public Node connect3(Node root) {
        if (root == null) return null;

        if (root.leftChild != null) { // update left next
            if (root.rightChild != null)
                root.leftChild.next = root.rightChild; // if right child exists - simple connect left.next to right
            else
                root.leftChild.next = findNext(root); // if not - scan parent next node until we find the first left or right child
        }
        if (root.rightChild != null) { // update right next
            root.rightChild.next = findNext(root);
        }

        // TODO: 2021/4/16
        connect(root.rightChild); // update the right nodes first
        connect(root.leftChild);
        return root;
    }

    private Node findNext(Node root) { // get parent node
        while (root.next != null) { // scan all next parent nodes until we find the first left or right child
            root = root.next;
            if (root.leftChild != null) return root.leftChild;
            if (root.rightChild != null) return root.rightChild;
        }
        return null;
    }

    //todo 原始层序遍历
}
