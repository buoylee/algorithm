package cn.buoy.leetcode.binarysearch;

import java.util.Arrays;
import java.util.Comparator;

public class Q354 {
    /**
     * 先去看300! 思路 部分与 300 相同.
     * <p>
     * 參考(其實沒有很好): https://www.youtube.com/watch?v=7phFRk34uBc&ab_channel=wenchaoguo
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        //envelopes[x][y] 设 i宽j高, 按照 x递增, y递减 sort.
        //關鍵: 爲什麼y可以递减排序, 因爲x的值可能相同, 如果y也遞增, 則需要加上判斷envelope[x] > envelope[0~(x-1)], 所以, 如果y按遞減, 不需要考慮x緯度, 完全當作300來做. https://www.youtube.com/watch?v=B1d2wV6mfzA, 時間點 15:13
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0])
                return arr2[1] - arr1[1];
            else
                return arr1[0] - arr2[0];
        });

        // 下邊使用了2分解法, 可以參考300題, 用n^2的寫法相對更易理解一點.
        // https://www.bilibili.com/video/BV1Mz4y1276W/?p=2&vd_source=43213e8a8aaeb48811d827b2f4ff6d04
        int dp[] = new int[envelopes.length];
        int len = 0;
        //利用了上边的排序, 只需要比宽即可, 高利用递增, 不需要判断同高的情况.
        //只能和不同宽的doll 组成递增序列.
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }

    // FIXME: 2021/5/2
    public void swap(int[][] envelopes, int i1, int i2) {
        int[] temp = envelopes[i1];
        envelopes[i1] = envelopes[i2];
        envelopes[i2] = temp;
    }

    public int partition(int[][] envelopes, int l, int h) {
        int pi = h;
        int i = l - 1;
        for (int j = l; j < h; j++) {
            if (envelopes[j][0] < envelopes[pi][0] || (envelopes[j][0] == envelopes[pi][0] && envelopes[j][1] > envelopes[pi][1])) {
                i++;
                swap(envelopes, j, i);
            }
        }
        swap(envelopes, pi, i + 1);
        return i + 1;
    }

    public void quickSort(int[][] envelopes, int l, int h) {
        if (l >= h) return;
        int pi = partition(envelopes, l, h);
        quickSort(envelopes, l, pi - 1);
        quickSort(envelopes, pi + 1, h);
    }

    public int binarySearch(int[] dp, int l, int h, int n) {
        if (h == 0) return 0;
        if (l > h - 1 || n > dp[h - 1]) {
            return -h;
        }
        int i = (h - l) / 2;
        System.out.println(i);
        if (dp[i] == n) {
            return i;
        } else if (dp[i] < n) {
            return binarySearch(dp, i + 1, h, n);
        } else {
            return binarySearch(dp, l, i, n);
        }
    }

    public int maxEnvelopes2(int[][] envelopes) {
        int res = 0;
        int[] dp = new int[envelopes.length];

        quickSort(envelopes, 0, envelopes.length - 1);

        for (int[] envelope : envelopes) {
            int i = Arrays.binarySearch(dp, 0, res, envelope[1]);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = envelope[1];
            if (i == res) res++;
        }

        // for (int i= 0; i < envelopes.length; i++) {
        //     int h= envelopes[i][1];
        //     int dpMax= 0;
        //     // for (int j= 0; j < envelopes.length; j++) {
        //     //     if (dp[j] > dpMax && envelopes[j][0] < w && envelopes[j][1] < h) {
        //     //         dpMax= dp[j];
        //     //     }
        //     }
        //     dp[i]= dpMax+1;
        //     if (dpMax+1 > res) res= dpMax+1;
        // if (w > wCurr && h > hCurr) {
        //     res++;
        //     wCurr= w;
        //     hCurr= h;
        // }


        return res;
    }
}
