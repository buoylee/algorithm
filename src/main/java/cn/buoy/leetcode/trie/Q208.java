package cn.buoy.leetcode.trie;

public class Q208 {
    /**
     * 簡單, 快速/跳過視頻, 直接看代碼.
     * https://www.youtube.com/watch?v=1gR5FfipQXM
     * 思路: 前置樹 實現.
     */
    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 從 root 開始, 從頭檢查 word 的每一個 letter, node != null 的話, 對應 "字母 offset" 插入 node 即可.
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.nextLetter[c - 'a'] == null)
                    node.nextLetter[c - 'a'] = new TrieNode();
                node = node.nextLetter[c - 'a'];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            // 從 root 開始, 從頭檢查 word 的每一個 letter 的 node != null, 且 最後一個 letter.isEnd == true 即可.
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.nextLetter[c - 'a'] == null)
                    return false;
                node = node.nextLetter[c - 'a'];
            }
            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            // 從 root 開始, 從頭檢查 prefix 的每一個 letter 的 node != null 即可 .
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                if (curr.nextLetter[c - 'a'] == null)
                    return false;
                curr = curr.nextLetter[c - 'a'];
            }
            return true;
        }

        /**
         * children 存在组合的下一个字母. 有单词, 以下一个字母 之前的字母组合 为前缀存在.
         * isEnd 到当前 node 是否有 单词(完整單詞的最后字母).
         */
        class TrieNode {
            private TrieNode[] nextLetter;
            private boolean isEnd;

            public TrieNode() {
                nextLetter = new TrieNode[26];
                isEnd = false;
            }
        }
    }

    // 上邊更簡潔
    class Trie2 {
        private final Node root;

        Trie2() {
            //相当于dummy head
            root = new Node('?', false);
        }

        public void insert(String val) {
            int n = val.length();
            Node currNode = root;
            int wordIdx = 0;
            while (wordIdx < n) {
                char c = val.charAt(wordIdx);

                if (currNode.next == null)
                    currNode.next = new Node[26];

                //放入与`a`相对位置
                if (currNode.next[c - 'a'] == null)
                    currNode.next[c - 'a'] = new Node(c, false);
                currNode = currNode.next[c - 'a'];
                wordIdx++;
            }

            currNode.isLeaf = true;
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
        class Node {
            char val;
            boolean isLeaf;
            Node[] next;

            Node(char val, boolean isLeaf) {
                this.val = val;
                this.isLeaf = isLeaf;
            }
        }
    }
}
