package cn.buoy.leetcode.array;

public class Q243 {

    /*
    自己做出来的, 画图就懂了!
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int index = -1, minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            //找到2关键字之一,则标记
            if (words[i].equals(word1) || words[i].equals(word2)) {
                //如果2 指针不同值 则计算最小值
                if (index != -1 && !words[index].equals(words[i])) {
                    minDistance = Math.min(minDistance, i - index);
                }
                index = i;
            }
        }
        return minDistance;
    }
}
