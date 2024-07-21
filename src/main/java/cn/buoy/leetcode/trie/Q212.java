package cn.buoy.leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q212 {
    /**
     * 簡單, 視頻, 註釋
     * https://www.youtube.com/watch?v=rSDG7mlk5iQ, 这个视频没细看, 当参考.
     * 思路: 先構建 trie, 然後 在矩陣任意一點 開始 dfs, 然後 4個方向延伸, 延伸一次, 就去 node.children 裏查, 有就繼續. 如果遇到有 node.word, 加入 result, 然後 node.word = null, 如果再遇到 同樣位置的解, 就不會重複加入.
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode trieRoot = buildTrie(words);
        // 遍歷整個 2維 arr
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                dfs(board, i, j, trieRoot, result);
        return result;
    }

    // 典型 上下左右 4個方向 dfs
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];
        // 關鍵: 用 '#' 表示 當前 這次查找 走過的格子, 避免 dfs 時, 重複走這些格子.
        // 檢查 "當前的 char" 有沒有存在在 "上個 char" 的 children 中.
        if (c == '#' || node.children[c - 'a'] == null) return;
        // 檢查下一個 letter
        node = node.children[c - 'a'];
        if (node.word != null) {
            result.add(node.word);
            // 關鍵: 刪除 node.word 下次就不會在添加這個 "最後letter格子組成的 完整 word"
            node.word = null; // 避免重复添加
        }
        board[i][j] = '#';
        // 格子邊界限制
        if (i > 0) dfs(board, i - 1, j, node, result);
        if (j > 0) dfs(board, i, j - 1, node, result);
        if (i < board.length - 1) dfs(board, i + 1, j, node, result);
        if (j < board[0].length - 1) dfs(board, i, j + 1, node, result);
        // backtracking
        board[i][j] = c;
    }

    /**
     * 典型構建字典數
     */
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new TrieNode();
                node = node.children[c - 'a'];
            }
            node.word = word;
        }
        return root;
    }

    private class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        // 關鍵: 如果遇到有 node.word, 加入 result, 然後 node.word = null, 如果再遇到 同樣位置的解, 就不會重複加入.
        private String word;
    }

//    class TrieNode {
//
//        //为了删除多余节点
//        TrieNode parent = null;
//        HashMap<Character, TrieNode> children = new HashMap<>();
//
//        //node的字母
//        final char v;
//        //方便 知道 到当前node 是否有完成单词"XXX".
//        String word = null;
//
//        public TrieNode(char c) {
//            this.v = c;
//        }
//    }
//
//    TrieNode root;
//    List<String> result;
//
//    //board长宽
//    int m;
//    int n;
//
//    public List<String> findWords(char[][] board, String[] words) {
//        result = new ArrayList<>();
//        m = board.length;
//        n = board[0].length;
//
//        //插入需要查找的单词进trie
//        //dummy node
//        root = new TrieNode('0');
//        for (var word : words) {
//            addWord(word);
//        }
//
//        //在board中找到 存在在words的 单词组合.
//        boolean[][] visited = new boolean[board.length][board[0].length];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                dfs(board, i, j, visited, root);
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 和以往dfs没什么区别
//     *
//     * @param board
//     * @param i       当前遍历到的 高
//     * @param j       当前遍历到的 宽
//     * @param visited
//     * @param node
//     */
//    private void dfs(char[][] board, int i, int j, boolean[][] visited, TrieNode node) {
//        //board越界
//        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
//            return;
//        }
//
//        //当前board格子 是否 与 存在trie的 当前搜索的word 当前遍历到的字母 相同.
//        node = node.children.get(board[i][j]);
//        if (node == null) {
//            return;
//        }
//
//        //设置 board格子 已访问
//        visited[i][j] = true;
//        //如果当前node 就是单词结尾, 放入res, 并移除trie的word
//        //找到单词并不会停止, 继续找, 直到上边的条件不符合. 因为可能存在{"oa", "oaa"}这些情况.
//        if (node.word != null) {
//            result.add(node.word);
//            removeWord(node);
//        }
//
//        //将下一node传到下一层
//        dfs(board, i + 1, j, visited, node);
//        dfs(board, i - 1, j, visited, node);
//        dfs(board, i, j + 1, visited, node);
//        dfs(board, i, j - 1, visited, node);
//
//        //backtracking
//        visited[i][j] = false;
//    }
//
//    /**
//     * 为了去掉重复单词
//     *
//     * @param node
//     */
//    private void removeWord(TrieNode node) {
//        //表示找到该单词, 下次不再再遇到, 就不会往res add 重复str.
//        node.word = null;
//        //不用下边代码也可以, 时间比较慢
//        while (node.word == null && node.parent != null && node.children.isEmpty()) {
//            node.parent.children.remove(node.v);
//            node = node.parent;
//        }
//    }
//
//    private void addWord(String word) {
//        var node = root;
//        for (int i = word.length() - 1; i >= 0; i--) {
//            char c = word.charAt(i);
//            var next = node.children.computeIfAbsent(c, k -> new TrieNode(k));
//            next.parent = node;
//            node = next;
//        }
//        node.word = word;
//    }
}
