package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class Q68 {
    /**
     * 簡單, 明白思路的話, 代碼比較繁瑣. 視頻, 代碼
     * https://www.youtube.com/watch?v=qrZLQmL6fyI
     * 思路: n 個 word, n-1 個 分隔區域(由x個' '組成), 組成 長度 L 的一行.
     * 處理 空格整除分配後的餘數, 在 前邊的分隔區域 平均分配.
     */
    public List<String> fullJustify(String[] words, int L) {
        List<String> result = new ArrayList<String>();
        int wordArrIdx = 0;
        while (wordArrIdx < words.length) {
            // 新一行的 累計 len, 包括 空格.
            int tempRowStrLen = words[wordArrIdx].length();
            int nextWordIdx = wordArrIdx + 1;
            // 不斷 累計 word len, 直到 + next word len 後, 超過 L
            while (nextWordIdx < words.length) {
                // +1 表示 空格
                if (tempRowStrLen + 1 + words[nextWordIdx].length() > L)
                    break;
                // 累加
                tempRowStrLen += 1 + words[nextWordIdx].length();
                nextWordIdx++;
            }
            StringBuilder builder = new StringBuilder();
            // 0 1 2 3(next), 從 wordArrIdx 開始 到 nextWordIdx 前一位, 分隔位 數量(用來分隔 word).
            int divisions = nextWordIdx - wordArrIdx - 1;
            // if last line or number of words in the line is 1, left-justified
            // 沒有 word 了(連 next 都沒了) || 剩 next 一個
            if (nextWordIdx == words.length || divisions == 0) {
                // word 之間 補上 至少一個 空格
                for (int i = wordArrIdx; i < nextWordIdx; i++)
                    builder.append(words[i] + " ");
                // 去掉最後一個 空格, 因爲只補必要的 word 之間的空格.
                builder.deleteCharAt(builder.length() - 1);
                // 如果還不滿足 L, 補 末尾0 到 L.
                for (int i = builder.length(); i < L; i++)
                    builder.append(" ");
            } else {
                // middle justified
                // 每個 word 需要補的空格的 整除部分 = 該行 剩餘 需要補 幾個 空格 / 該行 word num
                int spaces = (L - tempRowStrLen) / divisions;
                // 餘數
                int r = (L - tempRowStrLen) % divisions;
                for (int i = wordArrIdx; i < nextWordIdx; i++) {
                    builder.append(words[i]);
                    if (i < nextWordIdx - 1) {
                        // 整除部分(+1 是因爲 tempRowStrLen 先前要滿足 至少有1個' ' 的前提條件)
                        for (int j = 0; j < spaces + 1; j++)
                            builder.append(" ");
                        // 餘數分配
                        if (r > 0)
                            builder.append(" ");
                        r--;
                    }
                }
            }
            result.add(builder.toString());
            // 下一行的 起點
            wordArrIdx = nextWordIdx;
        }
        return result;
    }

    // TODO: 2021/4/19
    public List<String> fullJustify2(String[] words, int maxWidth) {
        int left = 0;
        List<String> result = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth)
            sum += 1 + words[right++].length();

        return right - 1;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right - left == 0) return padResult(words[left], maxWidth);

        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);

        String space = isLastLine ? " " : blank(totalSpace / numSpaces);
        int remainder = isLastLine ? 0 : totalSpace % numSpaces;

        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++) wordsLength += words[i].length();
        return wordsLength;
    }

    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    private String blank(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }

    public static void main(String[] args) {
        Q68 q68 = new Q68();
        q68.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
    }
}
