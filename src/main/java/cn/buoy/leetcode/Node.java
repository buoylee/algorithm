package cn.buoy.leetcode;


public class Node {
    public int val;
    public Node leftChild;
    public Node rightChild;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        leftChild = _left;
        rightChild = _right;
        next = _next;
    }
}
