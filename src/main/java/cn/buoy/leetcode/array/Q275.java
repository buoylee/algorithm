package cn.buoy.leetcode.array;


public class Q275 {
    /**
     * 简单, 视频, 需要复习
     * https://www.youtube.com/watch?v=qptGYoQmJN0
     * 思路: 排过序, 第一时间想到2分. 关键还是, 保证 检查过的 paper 数量(从1开始) <= "当前遍历到的 paper 的 citation"
     */
    public int hIndex(int[] citations) {
        int len = citations.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 关键: 保证 检查过的 paper 数量(从1开始) <= "当前遍历到的 paper 的 citation"
            // paper数: len - mid;
            // 遍历到的 paper citation: citations[mid];
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) { // paper数 > 当前遍历到的 paper 的 citation, 只能往右找.
                left = mid + 1;
            } else
                right = mid - 1;
        }
        // 都找不到, 只能选合法的范围
        return len - left;
    }
}
