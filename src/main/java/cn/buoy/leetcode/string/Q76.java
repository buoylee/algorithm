package cn.buoy.leetcode.string;

public class Q76 {
    /**
     * https://www.youtube.com/watch?v=63i802XLgOM
     */
    public String minWindow(String s, String t) {
        //如果要满足substr的话, 每个char还需要多少个的list.
        int[] NotMatchCharMap = new int[128];
        for (char c : t.toCharArray()) {
            NotMatchCharMap[c]++;
        }
        //counter 是 满足的substring 的char个数. 这里用来表示 还没满足的char个数.
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (NotMatchCharMap[c1] > 0) counter--;
            NotMatchCharMap[c1]--;
            end++;
            //一旦满足了达成条件的要求, 开始从start减少, 直到在次不满足.
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                final char c2 = s.charAt(start);
                //还回去
                NotMatchCharMap[c2]++;
                //只有少于了 要求的次数, 即NotMatchCharMap 出现了正数的时候, 才需要update 这个count. (因为这个substr可能多包括了某个char,去掉一个不影响substr满足的条件, 忘了去看下视频!)
                if (NotMatchCharMap[c2] > 0) counter++;
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}

