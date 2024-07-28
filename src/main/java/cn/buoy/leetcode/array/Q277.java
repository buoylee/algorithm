package cn.buoy.leetcode.array;

/**
 * 知道原理就简单, 视频, 注释
 * https://www.youtube.com/watch?v=wtQwckmEXR0
 * 思路: 通过2个条件筛选名人. 1. a认识b, a肯定不是candidate; 2. a不认识b, b肯定不是candidate.
 * 具体过程看注释.
 */
public class Q277 {
    public int findCelebrity(int n) {
        // 拿第一个作为 候选者
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            // 关键: 如果 candidate know i, 则 candidate 一定不是 名人; 把 candidate 替换成 i(有可能是名人);
            // 关键: 当循环结束, candidate == i, 只能说明 i 对 [i + 1, n - 1]都不认识, 且 [0, i -1] 的人 肯定不是 名人;
            // 关键: 但是没法证明 i 就是 名人, 因为没有用 i 来检查 是否认识 [0, i -1] 的人.
            if (knows(candidate, i))
                candidate = i;
        }
        for (int i = 0; i < n; i++) {
            // 关键: 检查 [0, i -1] 区域, 一旦发现 candidate 认识 其他人, 或者 其他人 不认识 candidate, false;
            if (i < candidate && knows(candidate, i) || !knows(i, candidate))
                return -1;
            // 关键: 检查 [i + 1, n - 1] 区域, 该区域确认 candidate 都不认识 其他人, 所以, 只需要验 这个区域的其他人 是否认识 candidate 即可.
            if (i > candidate && !knows(i, candidate))
                return -1;
        }
        return candidate;
    }

    // dummy
    public boolean knows(int a, int b) {
        return false;
    }
}
