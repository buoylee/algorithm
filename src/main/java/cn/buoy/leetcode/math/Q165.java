package cn.buoy.leetcode.math;

public class Q165 {
    /**
     * 简单, 視頻,  註釋.
     * https://www.youtube.com/watch?v=ycgUISujdMI
     * 思路: 用'.'分割, 從頭開始比, 直到到達 較長size的那個 尾部(或者提前比較出大小), 較短的比較到爲null的時候, 用0替代.
     * 注意一些testcase:
     * 1.01, 1.001 是相等的.
     */
    public int compareVersion(String version1, String version2) {
        //用'.'分割
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");
        // 需要取較大的size, 一定要比到 較大size最後, 例如: 1.0, 1.0.0; 1.0, 1.0.1; 較短的比較到爲null的時候, 用0替代.
        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            //超過長度用0來比.
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            //大小返回 1, -1, 相等就0
            int compare = v1.compareTo(v2);
            // 一旦出現不相等, 就出結果.
            if (compare != 0)
                return compare;
        }
        return 0;
    }
}
