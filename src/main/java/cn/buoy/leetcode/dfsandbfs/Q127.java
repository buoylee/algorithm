package cn.buoy.leetcode.dfsandbfs;

import java.util.*;

/*
https://www.youtube.com/watch?v=vWPCm69MSfs
 */
public class Q127 {

    public static void main(String[] args) {
        Q127 q127 = new Q127();
//        ArrayList<String> wordList = new ArrayList<String>;
        String[] strings = {"hot", "dot", "dog", "lot", "log", "cog"};
//        boolean b = wordList.add(strings);
        List list = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        q127.ladderLength("hit", "cog", list);
    }


    /*
    BFS
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //加快查询
        Set<String> dict = new HashSet<>(wordList), vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        //len记录步数
        for (int len = 1; !q.isEmpty(); len++) {
            //一直弹, 弹到没有
            for (int i = q.size(); i > 0; i--) {
                String w = q.poll();
                //等于 endWord, 直接返回步数.
                if (w.equals(endWord))
                    return len;
                //对当前弹出的单词从词首到词尾字母进行替换.
                for (int j = 0; j < w.length(); j++) {
                    char[] ch = w.toCharArray();
                    //替换不同字母
                    for (char c = 'a'; c <= 'z'; c++) {
                        //跳过对比位于原字母相同的.
                        if (c == w.charAt(j))
                            continue;
                        ch[j] = c;
                        String nb = String.valueOf(ch);
                        //拿替换好的单词检查是否存在于dict, 有则加入.
                        if (dict.contains(nb) && vis.add(nb))
                            q.offer(nb);
                    }
                }
            }
        }
        return 0;
    }


    /*
    双向BFS
    https://leetcode.com/problems/word-ladder/discuss/40711/Two-end-BFS-in-Java-31ms.

    原理:?
    加入到start, end set 的元素 都是 start, end 能到达的元素
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        Set<String> wordAsList = new HashSet<>(wordList);
        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();
        int length = 1;
        start.add(beginWord);
        end.add(endWord);
        wordAsList.remove(beginWord);
        wordAsList.remove(endWord);

        while (!start.isEmpty()) {
            Set<String> next = new HashSet<>();
            for (String word : start) {
                char[] wordArray = word.toCharArray();
                //每一位比较
                for (int i = 0; i < word.length(); i++) {
                    char old = wordArray[i];
                    //每一位不同字母比较
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[i] = c;
                        String str = String.valueOf(wordArray);
                        //准备在start插入的str, 如果存在于end, 则完成.
                        if (end.contains(str))
                            return length + 1;
                        //如果不存在于end, 则检查wordAsList是否有, 有则加入start.
                        if (wordAsList.contains(str)) {
                            next.add(str);
                            wordAsList.remove(str);
                        }
                    }
                    wordArray[i] = old;
                }
            }
            start = next.size() < end.size() ? next : end;
            end = start.size() < end.size() ? end : next;
            length++;
        }
        return 0;
    }
}
