package cn.buoy.leetcode.string;

public class Q6 {
    /**
     * 超簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=y67AxS__8XE
     * 思路: 不用想太多, 不要給圖搞混, 其實就是 '上到下, 然後下到上'放置字符.
     */
    public String convert(String s, int numRows) {
        // 構建 n 行 StringBuilder.
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            sbs[i] = new StringBuilder();
        // 初始 str index.
        int i = 0;
        while (i < s.length()) {
            // 从上到下
            int rowIndex = 0;
            while (i < s.length() && rowIndex <= numRows - 1) {
                sbs[rowIndex].append(s.charAt(i));
                i++;
                rowIndex++;
            }
            // 細節: 回頭時, 需要跳2步, 超出 s.length - 1 一步(因爲上個while最後多一個i++), s.length - 1 一步.
            rowIndex -= 2;
            // 回頭
            while (i < s.length() && rowIndex >= 1) {
                sbs[rowIndex].append(s.charAt(i));
                i++;
                rowIndex--;
            }
        }
        // result: append 每一行str
        for (int j = 1; j < numRows; j++)
            sbs[0].append(sbs[j].toString());
        return sbs[0].toString();
    }
}
