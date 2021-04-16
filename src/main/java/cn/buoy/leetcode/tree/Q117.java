package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.Node;

public class Q117 {
    /**
     * https://www.youtube.com/watch?v=W2xmj9ZYaHE
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node res = root;
        Node head = root;//The left most node in the lower level
        Node prev = null;//The previous node in the lower level
        Node curr = null;//The current node in the upper level
        while (head != null) {
            //每一层开始, 用 cur 替代 上次上层求出的本层的head, 并将head = null, 等待第一个出现的 后代node. 作为下一层的head.
            curr = head;
            prev = null;
            head = null;
            //直到同层到了末尾
            while (curr != null) {
                if (curr.left != null) {
                    //如果有prev, 连上即可.
                    if (prev != null)
                        prev.next = curr.left;
                    else
                        //如果prev == null, 说明这层循环刚开始, left有值, 则设置为head.
                        //关键在这, 这里就将 下层的head 找到了. 遍历上层时, 第一个出现的就是 head. 同理right node.
                        head = curr.left;
                    prev = curr.left;
                }
                //同理上边
                if (curr.right != null) {
                    if (prev != null)
                        prev.next = curr.right;
                    else
                        head = curr.right;
                    prev = curr.right;
                }
                //遍历同层下一个.
                curr = curr.next;
            }
        }
        return res;
    }

    /**
     * 写法不同而已
     *
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null) return null;

        if (root.left != null) { // update left next
            if (root.right != null)
                root.left.next = root.right; // if right child exists - simple connect left.next to right
            else
                root.left.next = findNext(root); // if not - scan parent next node until we find the first left or right child
        }
        if (root.right != null) { // update right next
            root.right.next = findNext(root);
        }

        // TODO: 2021/4/16
        connect(root.right); // update the right nodes first
        connect(root.left);
        return root;
    }

    private Node findNext(Node root) { // get parent node
        while (root.next != null) { // scan all next parent nodes until we find the first left or right child
            root = root.next;
            if (root.left != null) return root.left;
            if (root.right != null) return root.right;
        }
        return null;
    }

    //todo 原始层序遍历
}
