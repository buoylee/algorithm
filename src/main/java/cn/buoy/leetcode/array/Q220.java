package cn.buoy.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class Q220 {
    /**
     * 巧妙, 可以复习
     * https://www.bilibili.com/video/BV1fK411N7tU?from=search&seid=14543199140093539340
     * 思路: 桶排序, 通過把值分配到桶大小爲 valueDiff + 1 中(为了使 桶内元素的最大差值 <= valueDiff, 便于检查在 valueDiff 范围内, 是否有目标 indexDiff),
     * 如果同桶没有, 只需要檢查 "當前 num 的桶" 和 "前/後鄰居桶的值" 即可, 減少了部分遍歷.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0) return false;
        // 存放 num 和 num 最近一次出现的 index
        Map<Long, Long> numToLatestIndex = new HashMap<>();
        // 关键: 假如相隔小于等于5, 即0-5, 6个数, 需要将数据每6个分一组, 所以需要 "桶的大小" 为 valueDiff + 1.
        long bucketWidth = (long) valueDiff + 1;
        for (int i = 0; i < nums.length; ++i) {
            long bucketID = getBucketID(nums[i], bucketWidth);
            // 同一桶, 直接返回
            if (numToLatestIndex.containsKey(bucketID))
                return true;
            // 对比前一桶, 如果出现在隔壁桶, 还需要检查, 是否在 valueDiff 内.
            if (numToLatestIndex.containsKey(bucketID - 1) && Math.abs(nums[i] - numToLatestIndex.get(bucketID - 1)) < bucketWidth)
                return true;
            // 同理, 对比后一桶
            if (numToLatestIndex.containsKey(bucketID + 1) && Math.abs(nums[i] - numToLatestIndex.get(bucketID + 1)) < bucketWidth)
                return true;
            //只有桶中没有元素, 才需要放入, 不然在第一步就已经返回true
            numToLatestIndex.put(bucketID, (long) nums[i]);
            if (i >= indexDiff)
                numToLatestIndex.remove(getBucketID(nums[i - indexDiff], bucketWidth));
        }
        return false;
    }

    private long getBucketID(long num, long bucketWidth) {
        // 当 num >= 0 时, num / bucketWidth 选择的桶是正确的
        // 关键: 但当 num < 0, num / bucketWidth 会 将 -(bucketWidth - 1) ~ 0 放在同一个桶, 也就是 负数时, 整体的选桶都偏右移了1位(因为没有 -0), 所以, 给 负数 选桶时, 需要全部左移一位.
        return num < 0 ? (num + 1) / bucketWidth - 1 : num / bucketWidth;
    }

    /**
     * https://www.youtube.com/watch?v=VqpYCLChvio
     * 用了treeSet, 写法更简单, 速度稍慢
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long) nums[i]);
            Long ceiling = set.ceiling((long) nums[i]);

            if (floor != null && (long) nums[i] - floor <= valueDiff) return true;
            if (ceiling != null && ceiling - (long) nums[i] <= valueDiff) return true;

            set.add((long) nums[i]);

            if (set.size() > indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }

}
