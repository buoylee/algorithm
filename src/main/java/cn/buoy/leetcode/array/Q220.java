package cn.buoy.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class Q220 {

    /*
    https://www.bilibili.com/video/BV1fK411N7tU?from=search&seid=14543199140093539340
    桶排序, 通過把值分配到桶大小爲t+1中, 然後只需要檢查當前值的桶和前後鄰居桶的值即可, 減少了部分遍歷.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        //假如相隔小于等于5, 即0-5, 6个数, 需要将数据每6个分一组, 所以需要 t + 1.
        long bucketWidth = (long) valueDiff + 1;
        for (int i = 0; i < nums.length; ++i) {
            long bucketID = getBucketID(nums[i], bucketWidth);
            //同一桶, 直接返回
            if (d.containsKey(bucketID))
                return true;
            //对比前一捅
            if (d.containsKey(bucketID - 1) && Math.abs(nums[i] - d.get(bucketID - 1)) < bucketWidth)
                return true;
            //对比后一捅
            if (d.containsKey(bucketID + 1) && Math.abs(nums[i] - d.get(bucketID + 1)) < bucketWidth)
                return true;
            //只有桶中没有元素, 才需要放入, 不然在第一步就已经返回true
            d.put(bucketID, (long) nums[i]);
            if (i >= indexDiff) d.remove(getBucketID(nums[i - indexDiff], bucketWidth));
        }
        return false;
    }

    private long getBucketID(long num, long bucketWidth) {
        //因为少了-0, 所有负数, 全部左移一位
        return num < 0 ? (num + 1) / bucketWidth - 1 : num / bucketWidth;
    }

    /*
    https://www.youtube.com/watch?v=VqpYCLChvio
    用了treeSet, 写法更简单, 速度稍慢
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
