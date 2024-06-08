package cn.buoy.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://www.youtube.com/watch?v=rc2QdQ7U78I
 */
public class Q128 {
    /*
    视频 第一解, 更新每一块的左右边界value(这块连续数字的最长个数, left=right=value), 还有自己作为左右邻居的情况.
     */
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            // if there is no duplicates, these two lines can be commented
            if (map.containsKey(nums[i])) continue;
            map.put(nums[i], 1);

            int end = nums[i];
            int begin = nums[i];
            if (map.containsKey(nums[i] + 1))
                end = nums[i] + map.get(nums[i] + 1);
            if (map.containsKey(nums[i] - 1))
                begin = nums[i] - map.get(nums[i] - 1);
            longest = Math.max(longest, end - begin + 1);
            map.put(end, end - begin + 1);
            map.put(begin, end - begin + 1);
        }
        return longest;
    }

    /*
    视频 第2解
    检查每个set元素, 找出所有可能连续的数字的左边界, 在开始统计最长连续数字串.
    如果set i index的值 - 1 存在于 set, 则不检查,
    否则检查 该值的 ++ ,直到 没有连续, 记录最大值.直到结束.
     */
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        int best = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {  // only check for one direction
                int m = n + 1;
                while (set.contains(m)) {
                    m++;
                }
                best = Math.max(best, m - n);
            }
        }
        return best;
    }
}
