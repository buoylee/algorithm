package cn.buoy.leetcode.string;

import java.util.HashMap;
import java.util.Stack;

public class Q316 {
    /**
     * 懂思路就簡單, 視頻, 註釋.
     * https://www.youtube.com/watch?v=-zmul9EyKng 思路具體清晰
     * 思路: 如果要滿足最小字典序, 需要每當發現 "當前 letter" 比 "棧頂 letter" 的字母序小, 且 "棧頂 letter" 還會在 "當前 letter" 後續出現, 則可以拋棄. 直到最後, 把 "當前 letter" 入棧, 繼續檢查 下一個 letter.
     */
    public String removeDuplicateLetters(String sr) {
        int[] letterFreq = new int[26]; //will contain number of occurences of character (i+'a')
        boolean[] visited = new boolean[26]; //will contain if character (i+'a') is present in current result Stack
        char[] charArr = sr.toCharArray();
        //count number of occurences of character
        for (char c : charArr)
            letterFreq[c - 'a']++;
        Stack<Character> stack = new Stack<>(); // answer stack
        int offset;
        for (char c : charArr) {
            offset = c - 'a';
            letterFreq[offset]--;   //decrement number of characters remaining in the string to be analysed
            if (visited[offset]) //if character is already present in stack, dont bother
                continue;
            //if current character is smaller than last character in stack which occurs later in the string again
            //it can be removed and  added later e.g stack = bc remaining string abc then a can pop b and then c
            // 关键: 每當發現 "當前 letter" 比 "棧頂 letter" 的字母序小, 且 "棧頂 letter" 還會在 "當前 letter" 後續出現, 則可以拋棄.
            while (!stack.isEmpty() && c < stack.peek() && letterFreq[stack.peek() - 'a'] != 0)
                visited[stack.pop() - 'a'] = false;
            // 直到最後, 把 "當前 letter" 入棧
            stack.push(c); //add current character and mark it as visited
            visited[offset] = true;
        }
        StringBuilder sb = new StringBuilder();
        //pop character from stack and build answer string from back
        while (!stack.isEmpty())
            sb.insert(0, stack.pop());
        return sb.toString();
    }

    /**
     * https://www.youtube.com/watch?v=gs6L_q1QRdM 短, 講的沒有上邊有思路, 先看上邊的就好
     */
    public String removeDuplicateLetters2(String s) {
        if (s == null || s.length() == 0) return s;
        // 0 1 2 3 4 5 6 7
        // c b a c d c b c
        // map
        // a : 2
        // c : 7
        // d : 4
        // b : 6
        HashMap<Character, Integer> map = new HashMap<>();

        // 每个元素只保存最后的位置
        // 它必须在这个位置之前出现, 否则就没法出现了
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        // 一共几种字母
        char[] res = new char[map.size()];

        int start = 0;
        // 找到map中的最小值
        // a : 2
        // end = 2
        int end = findMinLastPos(map);

        // 每次往结果中加入一个, 同时更新map和end
        for (int i = 0; i < res.length; i++) {
            char minChar = 'z' + 1;
            // 找到当前片段中的最小字符
            for (int k = start; k <= end; k++) {
                // map中还有这个字符, 表示res中还没有
                if (map.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    start = k + 1;
                }
            }

            res[i] = minChar;
            map.remove(minChar);
            // 如果这个字符是map中的最小字符, 需要更新map
            if (s.charAt(end) == minChar) {
                end = findMinLastPos(map);
            }
        }

        return new String(res);
    }

    private int findMinLastPos(HashMap<Character, Integer> map) {
        this.map = map;
        int res = Integer.MAX_VALUE;
        for (int num : map.values()) {
            res = Math.min(res, num);
        }

        return res;
    }
}
