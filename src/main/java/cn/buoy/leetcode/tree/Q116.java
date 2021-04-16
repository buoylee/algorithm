package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.Node;

/**
 * https://www.youtube.com/watch?v=qGbyJBPXdcM
 * 简单. 关键在 记录head; 如果parent.next == null, 那么说 parent.right.next 也是 == null.
 */
public class Q116 {
    public Node connect(Node root) {
        Node res = root;
        while (root != null && root.left != null) {
            Node cur = root;
            while (cur != null) {
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                cur = cur.next;
            }
            root = root.left;
        }
        return res;
    }
}
