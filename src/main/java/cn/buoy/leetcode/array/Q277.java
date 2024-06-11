package cn.buoy.leetcode.array;

/**
 * https://www.youtube.com/watch?v=wtQwckmEXR0
 * 思路: a认识b, a肯定不是candidate;  a不认识b, b肯定不是candidate.
 */
public class Q277 {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            //candidate know 任何人(除自己), ca则不可能是Celebrity;
            //如果a unknow b, 则 b 也不可能是 candidate;
            if (knows(candidate, i))
                candidate = i;
        }


//        for (int i = 0; i < n; i++) {
//            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
//        }

        for (int i = 0; i < n; i++) {
            if (i < candidate && knows(candidate, i) || !knows(i, candidate))
                return -1;
            //大于 candidate 的部分在 first loop 已验过, 只需要验其他人是否认识 candidate.
            if (i > candidate && !knows(i, candidate))
                return -1;
        }

        return candidate;
    }

    public boolean knows(int a, int b) {
        //todo
        return false;
    }
}
