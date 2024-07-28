package cn.buoy.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q219 {
    /**
     * 如果知道可以用 滑动窗口 解, 下一个写法感觉更直观. 这个也很直观, 简单, 视频.
     * https://www.youtube.com/watch?v=BiQ1twbSQiY&t=10s
     * 思路: 遍历 nums 放入 map(记录 num 和 num 上次出现的 index),
     * 每当出现 重复 ele, 检查 上次出现 "相同 ele" 的 index 距离 是否超过 k,
     * 没超过就有解,
     * 超过则, 更新 该 num 的 index, 用于下次出现 "相同 ele" 时, 比较 index 跨度.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // k 宽度内, 有 相同 ele, 有解.
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                return true;
            // 不在 k 宽度内, 需要更新 num 的 index, 用于下次出现 "相同 ele" 时, 比较 index 跨度.
            map.put(nums[i], i);
        }
        // 到尾都没找到 k宽度 内有 "相同 ele", false
        return false;
    }

    /**
     * 思路: 滑动窗口. 遍历 nums 插入 set,
     * 当 窗口宽度(set.size) 超过k, 先删掉最前边(刚离开窗口)的 元素, 然后 插入 curr ele,
     * 插入失败, 说明有解, 返回true,
     * 遍历完整个 nums, 没有就 false.
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) // 关键: 这样判断是可以知道 "是否超过窗口宽度" 的.
                set.remove(nums[i - k - 1]);
            // 只要 有重复 ele, 说明 有解.
            if (!set.add(nums[i]))
                return true;
        }
        return false;
    }
}
