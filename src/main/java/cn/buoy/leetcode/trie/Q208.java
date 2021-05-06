package cn.buoy.leetcode.trie;

public class Q208 {
    /**
     *https://www.youtube.com/watch?v=1gR5FfipQXM
     */
}

class Trie {

    private final Node root;

    Trie() {
        //相当于dummy head
        root = new Node('?', false);
    }

    public void insert(String val) {
        int n = val.length();
        Node ptr = root;
        int curr = 0;

        while (curr < n) {
            char c = val.charAt(curr);

            if (ptr.next == null)
                ptr.next = new Node[26];

            //放入与`a`相对位置
            if (ptr.next[c - 'a'] == null)
                ptr.next[c - 'a'] = new Node(c, false);
            ptr = ptr.next[c - 'a'];
            curr++;
        }

        ptr.isLeaf = true;
    }

    public boolean search(String val) {
        int n = val.length();
        Node ptr = root;
        int curr = 0;
        while (curr < n && ptr.next != null && ptr.next[val.charAt(curr) - 'a'] != null) {
            ptr = ptr.next[val.charAt(curr++) - 'a'];
        }
        //注意判断 isLeaf
        return curr == n && ptr.isLeaf;
    }

    //前缀不需要是完整单词, 不用判断 isLeaf
    public boolean startsWith(String val) {
        int n = val.length();
        Node ptr = root;
        int curr = 0;
        while (curr < n && ptr.next != null && ptr.next[val.charAt(curr) - 'a'] != null) {
            ptr = ptr.next[val.charAt(curr++) - 'a'];
        }
        return curr == n;
    }

    /**
     * val 当前node value
     * isLeaf 到当前node是否存在完整单词(单词最后字母).
     * next 存在组合的下一个字母. 有单词, 以下一个字母 之前的字母组合 为前缀存在.
     */
    static class Node {
        char val;
        boolean isLeaf;
        Node[] next;

        Node(char val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
        }
    }
}
