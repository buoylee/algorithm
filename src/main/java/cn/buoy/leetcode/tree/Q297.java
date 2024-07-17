package cn.buoy.leetcode.tree;

import cn.buoy.leetcode.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q297 {
    /**
     * 思路其實是簡單的, 分隔符"," 要想清楚
     * https://www.youtube.com/watch?v=1BbMhi2hxDM 前序
     * https://www.youtube.com/watch?v=usIDCL9cbus bfs
     * 思路: 前序遍歷, 寫起來比較短, 有沒 bfs 直觀.
     * 关键: 在于 如何处理 null 和 分隔符","
     */
    public String serialize(TreeNode root) {
        return serial(new StringBuilder(), root).toString();
    }

    // Generate preorder string
    private StringBuilder serial(StringBuilder str, TreeNode root) {
        // 2者種寫法差別在於:
        // 這樣寫 所有 node(包括 null) 後邊都是 加",", 導致 str 最後多一個",", 這樣寫也可以過, 因爲 反序列化的時候, 最後一個 ""(空字符)已無非"null"node可以插入, 所以不會影響
//        if (root == null) return str.append("#").append(",");
//        str.append(root.val).append(",");
//        serial(str, root.left);
//        serial(str, root.right);
        if (root == null) return str.append("#");
        str.append(root.val).append(",");
        // 這樣寫, 只會在出現最後的 null 時(如果 node 不是 null, 在上邊已經加","了), 只給 left null 加 ","(無論是 "只缺失 left node" 還是 "葉子節點")
        serial(str, root.left).append(",");
        serial(str, root.right);
        return str;
    }

    public TreeNode deserialize(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    // Use queue to simplify position move
    private TreeNode deserial(Queue<String> q) {
        String val = q.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(q);
        root.right = deserial(q);
        return root;
    }
}
