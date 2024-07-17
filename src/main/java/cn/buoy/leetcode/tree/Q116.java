package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.Node;

/**
 * 簡單, 視頻, 想不起來用 bfs 也可以.
 * https://www.youtube.com/watch?v=qGbyJBPXdcM
 * 思路: 记录每層的 head;
 * 根據 parent.next 是否 有元素, 決定 下一層 的元素 連接("同一個 root" 的 "child node" 左->右; "相鄰 root" 的 "child node" 右->左),
 * 當 parent.next == null, 跳到下一行 nextHead = head.left (上層的 head 的 left, 就是 下一層的 head)
 */
public class Q116 {
    public Node connect(Node root) {
        if (root == null) return root;
        Node currAsParent = root;
        while (currAsParent != null) {
            // 每層的 head
            Node head = currAsParent;
            while (currAsParent != null) {
                // 有 leftChild 就直接連 rightChild
                if (currAsParent.leftChild != null)
                    currAsParent.leftChild.next = currAsParent.rightChild;
                // 關鍵: 如果在 i + 1 層 的 currAsParent.right 存在, 且 i 層 的 的 currAsParent 右邊 還有 元素. 一畫圖就懂了.
                //     0
                // " /   \"
                //   a    b
                //  / \  / \
                // c   d e  f
                // 如果 a 右邊有 元素
                // 那麼, d 連 e.
                // 就是這麼簡單.
                if (currAsParent.rightChild != null && currAsParent.next != null)
                    currAsParent.rightChild.next = currAsParent.next.leftChild;
                // 一直遍歷 "當前行" 的 所有元素(next), 直到 currAsParent == null 時退出.
                currAsParent = currAsParent.next;
            }
            // 上層的 head 的 left, 就是 下一層的 head
            currAsParent = head.leftChild;
        }
        return root;
    }

    // 上邊比較直觀
    public Node connect2(Node root) {
        Node result = root;
        while (root != null && root.leftChild != null) {
            Node cur = root;
            while (cur != null) {
                cur.leftChild.next = cur.rightChild;
                cur.rightChild.next = cur.next == null ? null : cur.next.leftChild;
                cur = cur.next;
            }
            root = root.leftChild;
        }
        return result;
    }
}
