package cn.buoy.leetcode.array;

public class Q327 {
    /**
     * https://www.youtube.com/watch?v=m9P1drvDjzY&t=182s
     * 视频讲的很好.
     * 思路: 转化为presums, 通过 lower <= presums[j]-presums[i] <= upper 找到合适的 (i, j)组合,
     * 拆分为2类问题, 1. (a, mid)或(mid+1, b)内的(i, j)匹配的数量; 2. (a, mid)中的i和(mid+1, b)中的j的组合.
     * 细节: 每当完成2个分片后, 可以归并排序, 因为`(a, mid)中的i和(mid+1, b)中的j的组合`这种情况, i肯定在j的左边.
     * <p>
     * 归并 就是分治法, 将大问题 拆成小问题解决.
     * merge后的一大段的组合数(前缀数组(j-i), i,j组合) 等于 2小段(在上次统计小段的时候, 找完组合数后, 排了序, 和归并一样) + 本merge中发现的组合时.
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        //因为例如sum = [2,1,4,6,3], preSum = [2,3,7,13,16], 你要得到前四个的preSums, 其实是需要用 preSums[3]-preSums[-1], 所以这里length+1, preSums[0] == 0.
        long[] preSums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            preSums[i + 1] = preSums[i] + nums[i];
        return countWhileMergeSort(preSums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] preSums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        //上2次分别求出的组合数.
        int count = countWhileMergeSort(preSums, start, mid, lower, upper)
                + countWhileMergeSort(preSums, mid, end, lower, upper);
        //因为左边已在上次递归中排序, 只会递增, 所以highLowerIndex和highUpperIndex都不需要归位到mid
        int rightLowerIndex = mid, rightUpperIndex = mid, mergesortRightIndex = mid;
        long[] cache = new long[end - start];
        //遍历左半边start->mid
        for (int mergeSortLeftIndex = start, sortedIndex = 0; mergeSortLeftIndex < mid; ++mergeSortLeftIndex, ++sortedIndex) {
            //在下层2小段时, 已经排过序(先求出组合, 再排的序, 排序是为了方便找出,i, j 2点, 分别在2小段中的情况下的组合.), 直接遍历到条件符合即可.
            //与i匹配的j下限
            while (rightLowerIndex < end && preSums[rightLowerIndex] - preSums[mergeSortLeftIndex] < lower)
                rightLowerIndex++;
            //与i匹配的j上限
            while (rightUpperIndex < end && preSums[rightUpperIndex] - preSums[mergeSortLeftIndex] <= upper)
                rightUpperIndex++;

            //在遍历左半边时, 顺便归并排序, 为了下次merge 找i,j分别在2组的组合时方便.
            while (mergesortRightIndex < end && preSums[mergesortRightIndex] < preSums[mergeSortLeftIndex])
                cache[sortedIndex++] = preSums[mergesortRightIndex++];
            cache[sortedIndex] = preSums[mergeSortLeftIndex];

            //sums[i] <= upper 多算一位, 不用+1
            count += rightUpperIndex - rightLowerIndex;
        }
        //这里传进来的end多1, 所以直接用mergesortHighIndex
        System.arraycopy(cache, 0, preSums, start, mergesortRightIndex - start);
        return count;
    }
}
