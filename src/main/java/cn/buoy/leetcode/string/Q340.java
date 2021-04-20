package cn.buoy.leetcode.string;

public class Q340 {
    /**
     * https://www.youtube.com/watch?v=XMXIX8kNknA
     * 视频中最后没必要清理, 按前边的遍历逻辑, 会先加入, 后判断是否超出k, 然后排除.
     *
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];     // there are 256 ASCII characters in the world

        int i = 0;  // i will be behind j
        int num = 0;
        int res = 0;

        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) {    // if count[s.charAt(j)] == 0, we know that it is a distinct character
                num++;
            }
            while (num > k && i < s.length()) {     // sliding window
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0) {
                    num--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
