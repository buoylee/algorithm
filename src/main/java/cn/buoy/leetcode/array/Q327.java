package cn.buoy.leetcode.array;

public class Q327 {
    /**
     * 道理算好理解, 歸併排序代碼實現不好寫.
     * https://www.youtube.com/watch?v=m9P1drvDjzY&t=182s
     * 视频讲的很好.
     * 思路: 分治法, nums 转化为 presums, 在 lower <= presums[j]-presums[i] <= upper 找到合适的 (i, j)组合,
     * 拆分为2类问题,
     * 1. [low, mid] 和 [mid+1, high] 各自區域内尋找 (i, j) 配對的数量;
     * 2. 完成分治的2個區域後, 先在各自區域排序, 然後分別在 "[low, mid] 中找 i" 和 "[mid+1, high] 中找 j" 組成配對的 數量.
     * 細節看註釋.
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        // 爲了方便代碼處理例如 index 0 ~ 2 範圍的 preSum, 需要 preSum[2] - preSum[-1], 所以增加了一個 dummy head.
        long[] preSums = new long[len + 1];
        // 構建 preSum[]
        for (int i = 0; i < len; ++i)
            preSums[i + 1] = preSums[i] + nums[i];
        return countWhileMergeSort(preSums, 0, len + 1, lower, upper);
    }

    /**
     * @param preSums
     * @param start
     * @param end
     * @param lower   要求 preSum 的最小值
     * @param upper   要求 preSum 的最大值
     * @return
     */
    private int countWhileMergeSort(long[] preSums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        // 關鍵: 這裏算是一種後續遍歷, 先遞歸處理下層, 下邊再處理當前合併後的區域.
        // 下層分治的2個區域 分别求出的 (i, j)组合数量.
        int count = countWhileMergeSort(preSums, start, mid, lower, upper) // left
                + countWhileMergeSort(preSums, mid, end, lower, upper); // right
        // 因为左/右2边已在上次递归中递增排序, 所以highLowerIndex和highUpperIndex都不需要归位到mid
        // 滿足 lower/upper的 右區域 index lower邊界
        int rightLowerIdx = mid;
        // 滿足 lower/upper的 右區域 index lower邊界 + 1
        int rightUpperIdx = mid;
        // 歸併排序時, 待插入的 右區域 元素
        int rightAreaIdx = mid;
        // 歸併排序時, 插入的位置
        int mergedIdx = 0;
        // 注意: 其實 cache 長度 比 start 到 end 長度少1, 因爲多了一個 dummy head.
        long[] cache = new long[end - start];
        // 找出 "(i, j) 2端點" 分别在左/右段中的情况下的组合
        // 遍历左半边, start -> mid
        for (int leftAreaIndex = start; leftAreaIndex < mid; leftAreaIndex++) {
            // 在處理下层2小段时, 已经排过序(先求出组合, 再排的序, 排序是为了方便找出 "(i, j) 2端點" 分别在左/右段中的情况下的组合.), 直接遍历到条件符合即可.
            // 求 滿足題目要求的 rightLowerIdx 和 rightUpperIdx 邊界.
            // 与當前 i 匹配的 j 的下限
            while (rightLowerIdx < end && preSums[rightLowerIdx] - preSums[leftAreaIndex] < lower)
                rightLowerIdx++;
            // 与當前 i 匹配的 j 的上限;
            // 關鍵: 爲什麼 代碼要寫 "<= upper", 如果要求滿足 lower <= preSum <= upper 的 preSum 個數, 如果用 (upper 的 idx - lower 的 idx) 其實個數是少1的, 所以這裏 rightUpperIdx 多了1.
            while (rightUpperIdx < end && preSums[rightUpperIdx] - preSums[leftAreaIndex] <= upper)
                rightUpperIdx++;
            // 一邊遍历左半边, 一邊归并排序. 归并排序, 是为了上層 merge 時, 找 "(i,j) 配對" j, j分别在各自的2组中 的组合 提供方便.
            // 關鍵: 進行部分的歸併排序, 因爲 當前 [leftAreaIndex] 和 "小於 [leftAreaIndex] 的 [rightAreaIdx]" 都是不再需要比較的 值, 可以先進行歸併排序
            while (rightAreaIdx < end && preSums[rightAreaIdx] < preSums[leftAreaIndex])
                cache[mergedIdx++] = preSums[rightAreaIdx++]; // 只插入 "小於 [leftAreaIndex] 的 [rightAreaIdx]". 注意, 這裏最後 rightAreaIdx 多 ++ 了一次.
            cache[mergedIdx] = preSums[leftAreaIndex]; // 補上 當前的 [leftAreaIndex]
            mergedIdx++;
            // 注意: 如果用 (upper 的 idx - lower 的 idx) 其實個數是少1的, 所以這裏 rightUpperIdx 多了1.
            count += rightUpperIdx - rightLowerIdx;
        }
        // 細節: 爲什麼是 rightAreaIdx - start. 只需要修改到 "小於 當前 [leftAreaIndex]" 的 rightAreaIdx - 1 (因爲 rightAreaIdx == 右區域需要移動的最後一個idx + 1)
        System.arraycopy(cache, 0, preSums, start, rightAreaIdx - start);
        return count;
    }

    private int countWhileMergeSort2(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort2(sums, start, mid, lower, upper) +
                countWhileMergeSort2(sums, mid, end, lower, upper);
        long[] cache = new long[end - start];
        int rightUpperIdx = mid, rightLowerIdx = mid, rightAreaIdx = mid, mergedIdx = 0;
        for (int leftAreaIndex = start; leftAreaIndex < mid; leftAreaIndex++) {
            while (rightLowerIdx < end && sums[rightLowerIdx] - sums[leftAreaIndex] < lower) rightLowerIdx++;
            while (rightUpperIdx < end && sums[rightUpperIdx] - sums[leftAreaIndex] <= upper) rightUpperIdx++;
            while (rightAreaIdx < end && sums[rightAreaIdx] < sums[leftAreaIndex])
                cache[mergedIdx++] = sums[rightAreaIdx++];
            cache[mergedIdx++] = sums[leftAreaIndex];
            count += rightUpperIdx - rightLowerIdx;
        }
        System.arraycopy(cache, 0, sums, start, rightAreaIdx - start);
        return count;
    }
}
