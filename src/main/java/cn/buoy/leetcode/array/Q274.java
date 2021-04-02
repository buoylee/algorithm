package cn.buoy.leetcode.array;

public class Q274 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int c : citations) {
            //桶排序, 统计不同引用数量, [ 0, 1, 2, 3, 4, 6->n ],
            if (c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            //累计数量大于等于index, 则返回.
            count += buckets[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }

    /*
    https://www.youtube.com/watch?v=6F307brfNDE
    先大到小排序, 后用index sorting.
     */
    public int hIndex2(int[] citations) {
        //todo
        return 0;
    }
}
