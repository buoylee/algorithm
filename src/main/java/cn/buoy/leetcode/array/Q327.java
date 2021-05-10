package cn.buoy.leetcode.array;

public class Q327 {
    /**
     * https://www.youtube.com/watch?v=m9P1drvDjzY&t=182s
     * 视频讲的很好.
     * 归并 就是分治法, 将大问题 拆成小问题解决.
     * merge后的一大段的组合数(前缀数组(j-i), i,j组合) 等于 2小段(在上次统计小段的时候, 找完组合数后, 排了序, 和归并一样) + 本merge中发现的组合时.
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        //上2次分别求出的组合数.
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            //在下层2小段时, 已经排过序(先求出组合, 再排的序, 排序是为了方便找出,i, j 2点, 分别在2小段中的情况下的组合.), 直接遍历到条件符合即可.
            while (k < end && sums[k] - sums[i] < lower) k++;
            while (j < end && sums[j] - sums[i] <= upper) j++;

            //这里就会普通归并排序, 为了下次merge 找i,j分别在2组的组合 时 方便.
            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
            cache[r] = sums[i];

            //sums[i] <= upper 多算一位, 不用+1
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
}
