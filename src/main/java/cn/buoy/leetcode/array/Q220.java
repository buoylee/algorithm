package cn.buoy.leetcode.array;

import java.util.HashMap;
import java.util.Map;


public class Q220 {

    /*
    https://www.bilibili.com/video/BV1fK411N7tU?from=search&seid=14543199140093539340
    桶排序
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        //假如相隔小于等于5, 即0-5, 6个数, 需要将数据每6个分一组, 所以需要 t + 1.
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            //同一桶, 直接返回
            if (d.containsKey(m))
                return true;
            //对比前一捅
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            //对比后一捅
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            d.put(m, (long) nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    private long getID(long i, long w) {
        //因为少了-0, 所有负数, 全部左移一位
        return i < 0 ? (i + 1) / w - 1 : i / w;
    }
}
