package cn.buoy.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q128 {
    /**
     * 这个解法其实更好想, 更直观
     * https://www.youtube.com/watch?v=rc2QdQ7U78I 第2解
     * https://www.youtube.com/watch?v=mJmkrW7AtwE 短, 思路一样, 实现稍微不同.
     * 思路: 先找下边界, 再找上边界.
     * 遍历 set, 每个 ele 都先判断是否 ele 存在有下邻居,
     * 有则跳过, 因为我们要从 下边界 开始累计, 才是最长的连续数列,
     * 直到我们找到 "没有下邻居" 的 ele, 这就是 连续数列的下边界, 这个时候, 我们开始累计最长连续数列长度.
     * 这样的思路是没问题的, 可能有多个 连续数列, 我们只会从作为 "下边界的 ele" 开始做 "累计最长连续数列长度".
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums)
            set.add(n);
        int result = 0;
        for (int num : set) {
            // 关键: 不断判断是否 num 有下邻居,
            if (!set.contains(num - 1)) {  // only check for one direction
                // 一旦发现 num 就是 lowerBound, 就开始计算 upperBound
                int upperBound = num;
                while (set.contains(upperBound))
                    upperBound++;
                result = Math.max(result, upperBound - num + 1); // 例: 1 2 3; lowerBound: 1, upperBound: 3; 所以 3-1 = 2, 需要多+1 就是长度.
            }
        }
        return result;
    }

    /**
     * 简单, 懂思路的话.
     * https://www.youtube.com/watch?v=rc2QdQ7U78I 第一解.
     * 思路: "每个 num" 都寻找是否有 "他的 左/右 neighbor", 通过记录 map<num, num 和 左/右 neighbor 组成连续数列的 长度>,
     * 我们可以得到 num 组成的连续数列 的 上下边界, 例如: num == 6, num 的 "下邻居的 组成连续数列的 长度" 为 2, 说明 num = 6 的 下边界就是, 6 - 2 = 4;
     * 如此我们可以不断更新已知的 num(只有 num 仍然作为 "边界" 的情况, 才更新 num 的长度(更新序列的中间元素没有意义)) 的 "组成连续数列的 长度"(要同时 检查上边界, 他们的和 就是 "组成连续数列的 长度")
     */
    public int longestConsecutive2(int[] nums) {
        int result = 0;
        // 关键: numToConsecutiveSeqLen 表示的是, num : "num 和 neighbor 组成连续数列长度.
        Map<Integer, Integer> numToConsecutiveSeqLen = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            // if there is no duplicates, these two lines can be commented
            if (numToConsecutiveSeqLen.containsKey(nums[i]))
                continue;
            numToConsecutiveSeqLen.put(nums[i], 1);
            // 寻找 "当前 num" 的边界时, init
            int low = nums[i];
            int high = nums[i];
            // 下邻居
            if (numToConsecutiveSeqLen.containsKey(nums[i] - 1))
                // 关键: 找到 下邻居 的下边界: "当前 num" - "下邻居 的 "和 neighbor 组成最长连续数列长度"
                low = nums[i] - numToConsecutiveSeqLen.get(nums[i] - 1);
            // 同理 上邻居
            if (numToConsecutiveSeqLen.containsKey(nums[i] + 1))
                high = nums[i] + numToConsecutiveSeqLen.get(nums[i] + 1);
            result = Math.max(result, high - low + 1);
            // 关键: 更新 当前 "num 和 neighbor 组成连续数列" 的上下边界的 长度.(如果没有 neighbor, high/low 其实就是 num自己)
            numToConsecutiveSeqLen.put(high, high - low + 1);
            numToConsecutiveSeqLen.put(low, high - low + 1);
        }
        return result;
    }
}
