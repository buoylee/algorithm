package cn.buoy.leetcode.binarysearch;

public class Q374 {
    /**
     * 这种循环会死循环, 如果是元素可能不存在的题目.
     * https://www.youtube.com/watch?v=VozMYFpDZO0
     */
    public int guessNumber(int n) {
        int lo = 1, hi = n;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (guess(mid) == 0) return mid;
            if (guess(mid) == -1)
                hi = mid;
            else
                lo = mid;
        }
        return guess(lo) == 0 ? lo : hi;
    }

    public int guessNumber2(int n) {
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (guess(mid) == 0) return mid;
            if (guess(mid) == -1)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return -1;
    }

    int guess(int num) {
        // FIXME: 2021/5/1 
        return 0;
    }
}
