package cn.buoy.leetcode.string;

public class Q358 {
    /**
     * https://www.youtube.com/watch?v=28ASDBKFTxw
     * 实现有出入, 思路差不多.
     *
     * @param str
     * @param k
     * @return
     */
    public String rearrangeString(String str, int k) {
        int length = str.length();
        int[] count = new int[26];
        //这个字母 的 下一个有效的位置
        int[] valid = new int[26];
        //统计字母频率
        for (int i = 0; i < length; i++) {
            count[str.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < length; index++) {
            int candidatePos = findValidMax(count, valid, index);
            if (candidatePos == -1) return "";
            count[candidatePos]--;
            valid[candidatePos] = index + k;
            sb.append((char) ('a' + candidatePos));
        }
        return sb.toString();
    }

    //选出剩余最多的 有效的 字母
    private int findValidMax(int[] count, int[] valid, int index) {
        //最大的 剩余频率
        int max = Integer.MIN_VALUE;
        //字母位置
        int candidatePos = -1;
        //其实已经是 按照 字母 排序来 找 最大了.
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] > max && index >= valid[i]) {
                max = count[i];
                candidatePos = i;
            }
        }
        return candidatePos;
    }
}
