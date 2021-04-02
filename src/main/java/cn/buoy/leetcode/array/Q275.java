package cn.buoy.leetcode.array;

public class Q275 {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int start = 0, end = len - 1;

        while (start < end) {
            int mid = (start + end) / 2;
            if (citations[mid] < len - mid) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        //要找到那个位置, citations[index](引用的数量) >= n - index(文章数), [0, 1, 3, 5, 6]
        if (start < len && citations[start] >= len - start)
            return len - start;
        else
            return 0;
    }
}
