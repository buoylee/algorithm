package cn.buoy.leetcode.math;

public class Q165 {
    /**
     * https://www.youtube.com/watch?v=ycgUISujdMI
     * 简单
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        //用 . 分
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        //遍历, str中直到最多点的那个长度
        //或 先找到 有大小的时候就返回
        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            //如果为短的为空了, 用0替代
            //其他转下int
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            //大小返回 1, -1, 相等就0
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
}
