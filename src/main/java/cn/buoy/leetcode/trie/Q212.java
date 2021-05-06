package cn.buoy.leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q212 {
    /**
     * https://www.youtube.com/watch?v=rSDG7mlk5iQ, 这个视频没细看, 当参考.
     */
    class TrieNode {

        //为了删除多余节点
        TrieNode parent = null;
        HashMap<Character, TrieNode> children = new HashMap<>();

        //node的字母
        final char v;
        //方便 知道 到当前node 是否有完成单词"XXX".
        String word = null;

        public TrieNode(char c) {
            this.v = c;
        }
    }

    TrieNode root;
    List<String> result;

    //board长宽
    int m;
    int n;

    public List<String> findWords(char[][] board, String[] words) {
        result = new ArrayList<>();
        m = board.length;
        n = board[0].length;

        //插入需要查找的单词进trie
        //dummy node
        root = new TrieNode('0');
        for (var word : words) {
            addWord(word);
        }

        //在board中找到 存在在words的 单词组合.
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, visited, root);
            }
        }
        return result;
    }

    /**
     * 和以往dfs没什么区别
     *
     * @param board
     * @param i       当前遍历到的 高
     * @param j       当前遍历到的 宽
     * @param visited
     * @param node
     */
    private void dfs(char[][] board, int i, int j, boolean[][] visited, TrieNode node) {
        //board越界
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
            return;
        }

        //当前board格子 是否 与 存在trie的 当前搜索的word 当前遍历到的字母 相同.
        node = node.children.get(board[i][j]);
        if (node == null) {
            return;
        }

        //设置 board格子 已访问
        visited[i][j] = true;
        //如果当前node 就是单词结尾, 放入res, 并移除trie的word
        //找到单词并不会停止, 继续找, 直到上边的条件不符合. 因为可能存在{"oa", "oaa"}这些情况.
        if (node.word != null) {
            result.add(node.word);
            removeWord(node);
        }

        //将下一node传到下一层
        dfs(board, i + 1, j, visited, node);
        dfs(board, i - 1, j, visited, node);
        dfs(board, i, j + 1, visited, node);
        dfs(board, i, j - 1, visited, node);

        //backtracking
        visited[i][j] = false;
    }

    /**
     * 为了去掉重复单词
     *
     * @param node
     */
    private void removeWord(TrieNode node) {
        //表示找到该单词, 下次不再再遇到, 就不会往res add 重复str.
        node.word = null;
        //不用下边代码也可以, 时间比较慢
        while (node.word == null && node.parent != null && node.children.isEmpty()) {
            node.parent.children.remove(node.v);
            node = node.parent;
        }
    }

    private void addWord(String word) {
        var node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            var next = node.children.computeIfAbsent(c, k -> new TrieNode(k));
            next.parent = node;
            node = next;
        }
        node.word = word;
    }
}
