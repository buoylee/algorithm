package cn.buoy.leetcode.string;

public class Q14 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=gvDxumXJGxY
     * 思路: 用第一個 str, 和 後續元素 比 是不是 common pre,
     * 比較過程: 如果 第一個 str 不是 第一個 str.next 的前綴, 循環去掉 第一個 str 最後一位, 直到是 str.next 的 pre,
     * 繼續拿這個 pre 和 下一個元素比, 同樣操作, 直到 最後元素.
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // 用第一個數去比, 巧妙. 反正是 common pre.
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // 不是前綴 就去掉最後一位, 直到 匹配這個 str pre 爲止.
            while (!strs[i].startsWith(pre))
                pre = pre.substring(0, pre.length() - 1);
            // 提早跳出? 速度一樣.
//            if (pre.isEmpty())
//                return pre;
        }
        return pre;
    }
}
