package cn.buoy.leetcode.string;

public class Q38 {
    /**
     * https://www.youtube.com/watch?v=0EGzSHEbXrQ
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n <= 0) return "-1";
        String result = "1";

        for (int i = 1; i < n; i++) {
            result = build(result);
        }
        return result;
    }

    private String build(String result) {
        StringBuilder builder = new StringBuilder();
        int p = 0;
        while (p < result.length()) {
            char val = result.charAt(p);
            int count = 0;

            //重点在这, 如果和第一个取的相同, count++; 否则 append count 和 value, 然后 count清0, 新一轮统计.
            while (p < result.length() &&
                    result.charAt(p) == val) {
                p++;
                count++;
            }
            builder.append(String.valueOf(count));
            builder.append(val);
        }
        return builder.toString();
    }
}
